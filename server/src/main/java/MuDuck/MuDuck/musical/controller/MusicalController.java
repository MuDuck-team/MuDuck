package MuDuck.MuDuck.musical.controller;

import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.musical.dto.Category;
import MuDuck.MuDuck.musical.dto.MusicalBoards;
import MuDuck.MuDuck.musical.dto.MusicalDto.MultiResponseDto;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.mapper.MusicalMapper;
import MuDuck.MuDuck.musical.service.MusicalService;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/musicals")
@Validated
@Slf4j
public class MusicalController {
    private final static String MUSICAL_DEFAULT_URL = "/musicals";
    private final MusicalService musicalService;
    private final MusicalMapper musicalMapper;

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

//    @GetMapping("/{musical-id}/actors")
//    public ResponseEntity getActors(@PathVariable("musical-id") @Positive Long musicalId){
////        List<ActorMusical> actorMusicalList = musicalService.findMusicalActors(musicalId);
////        return new ResponseEntity<>(musicalService.find(musicalId),HttpStatus.OK);
//        Musical response = musicalService.findMusicalActors(musicalId);
//        //List<Musical>actorMusicalList = new ArrayList<>();
//        return new ResponseEntity<>(musicalMapper.actorMusicalToMusicalResponseDto(response),HttpStatus.OK);
//    }
//
    @GetMapping("/{musical-id}/board")
    public ResponseEntity getBoards(@PathVariable("musical-id") @Positive Long musicalId){
        Musical muscial = musicalService.findMusical(musicalId);
        List<MusicalBoards> responseBoards = musicalService.findMusicalBoards(musicalId);
        Category responseCategory = musicalService.findCategoryName(musicalId);
        return new ResponseEntity<>(musicalMapper.boardsToMusicalResponseDtos(muscial, responseBoards, responseCategory), HttpStatus.OK);
    }

}
