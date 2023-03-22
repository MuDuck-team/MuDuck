package MuDuck.MuDuck.musical.controller;

import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.MultiResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.SingleResponseDto;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.mapper.MusicalMapper;
import MuDuck.MuDuck.musical.service.MusicalService;
import MuDuck.MuDuck.theater.entitiy.Theater;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musicals")
@Validated
@Slf4j
public class MusicalController {
    private final static String MUSICAL_DEFAULT_URL = "/musicals";
    private final MusicalService musicalService;
    private final MusicalMapper musicalMapper;
    //private final ActorMusical actorMusical;

//    public MusicalController(MusicalService musicalService, MusicalMapper musicalMapper, ActorMusical actorMusical) {
//        this.musicalService = musicalService;
//        this.musicalMapper = musicalMapper;
//        this.actorMusical = actorMusical;
//    }

    public MusicalController(MusicalService musicalService, MusicalMapper musicalMapper) {
        this.musicalService = musicalService;
        this.musicalMapper = musicalMapper;
    }
    @GetMapping
    public ResponseEntity getMusicals(@Positive @RequestParam int page, @Positive @RequestParam int size){
        Page<Musical> pageMusicals = musicalService.findMusicals(page - 1, size);
        List<Musical> musicals = pageMusicals.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals), pageMusicals), HttpStatus.OK);
    }
    @GetMapping(params = "genre")
    public ResponseEntity getMusicalGenres(@RequestParam(defaultValue = "GENRE_LICENSED") String genre,
            @Positive @RequestParam int page, @Positive @RequestParam int size){
        Page<Musical> pageMusicals = musicalService.findMusicalGenres(genre, page-1, size);
        List<Musical> musicals = pageMusicals.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals), pageMusicals), HttpStatus.OK);
    }
    @GetMapping(params = "sort")
    public ResponseEntity getMusicalFilters(@RequestParam(defaultValue = "musicalId") String sort,
            @Positive @RequestParam int page, @Positive @RequestParam int size){
        Page<Musical> pageMusicals = musicalService.findMusicalFilters(sort, page - 1, size);
        List<Musical> musicals = pageMusicals.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals), pageMusicals), HttpStatus.OK);
    }
    @GetMapping(params = "state")
    public ResponseEntity getMusicalStates(@RequestParam(defaultValue = "MUSICAL_ONAIR") String state,
            @Positive @RequestParam int page, @Positive @RequestParam int size){
        Page<Musical> pageMusicals = musicalService.findMusicalStates(state, page - 1, size);
        List<Musical> musicals = pageMusicals.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals), pageMusicals), HttpStatus.OK);
    }

//    @GetMapping("/{musical-id}")
//    public ResponseEntity getMusical(@PathVariable("musical-id") @Positive Long musicalId){
//        Musical response = musicalService.findMusical(musicalId);
//        Theater responseTheater = theaterService.find
//        return new ResponseEntity<>(new MultiResponseDto<>(musicalMapper.musicalToMusicalResponseDto(response),musicalMapper.theaterToMusicalResponseDto(theater)),HttpStatus.OK);
//    }

}
