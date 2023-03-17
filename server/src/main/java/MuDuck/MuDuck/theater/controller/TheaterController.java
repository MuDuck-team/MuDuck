package MuDuck.MuDuck.theater.controller;

import MuDuck.MuDuck.theater.dto.TheaterDto;
import MuDuck.MuDuck.theater.entitiy.Theater;
import MuDuck.MuDuck.theater.mapper.TheaterMapper;
import MuDuck.MuDuck.theater.service.TheaterService;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theaters")
@RequiredArgsConstructor
@Validated
public class TheaterController {
    private final TheaterService theaterService;
    private final TheaterMapper theaterMapper;

    @GetMapping
    public ResponseEntity getTheaters(){

        List<Theater> theaters = theaterService.getTheaters();

        List<TheaterDto.Response> responses = theaterMapper.theaterListToTheaterResponseList(theaters);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{theater-id}")
    public ResponseEntity getTheater(@Positive @PathVariable("theater-id") Long id){

        Theater theater = theaterService.getTheater(id);

        TheaterDto.Response response = theaterMapper.theaterToTheaterResponse(theater);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
