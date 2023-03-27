package MuDuck.MuDuck.musical.controller;

import MuDuck.MuDuck.actorMusical.service.ActorMusicalService;
import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentHead;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.mapper.BoardMapper;
import MuDuck.MuDuck.musical.dto.ActorMusicalDto.MappingActorResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.MappingResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.MultiResponseDto;
import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.Response;
import MuDuck.MuDuck.musical.mapper.MusicalMapper;
import MuDuck.MuDuck.musical.service.MusicalService;
import MuDuck.MuDuck.theater.entitiy.Theater;
import MuDuck.MuDuck.theater.mapper.TheaterMapper;
import MuDuck.MuDuck.theater.service.TheaterService;
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
    private final TheaterService theaterService;
    private final TheaterMapper theaterMapper;
    private final ActorMusicalService actorMusicalService;
    private final BoardMapper boardMapper;

    @GetMapping
    public ResponseEntity getMusicals(@Positive @RequestParam int page,
            @Positive @RequestParam int size) {
        Page<Musical> pageMusicals = musicalService.findMusicals(page - 1, size);
        List<Musical> musicals = pageMusicals.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals),
                        pageMusicals), HttpStatus.OK);
    }

    @GetMapping(params = "genre")
    public ResponseEntity getMusicalGenres(
            @RequestParam(defaultValue = "GENRE_LICENSED") String genre,
            @Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Musical> pageMusicals = musicalService.findMusicalGenres(genre, page - 1, size);
        List<Musical> musicals = pageMusicals.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals),
                        pageMusicals), HttpStatus.OK);
    }

    @GetMapping(params = "sort")
    public ResponseEntity getMusicalFilters(@RequestParam(defaultValue = "openDate") String sort,
            @Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Musical> pageMusicals = musicalService.findMusicalFilters(sort, page - 1, size);
        List<Musical> musicals = pageMusicals.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals),
                        pageMusicals), HttpStatus.OK);
    }

    @GetMapping(params = "state")
    public ResponseEntity getMusicalStates(
            @RequestParam(defaultValue = "MUSICAL_ONAIR") String state,
            @Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Musical> pageMusicals = musicalService.findMusicalStates(state, page - 1, size);
        List<Musical> musicals = pageMusicals.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals),
                        pageMusicals), HttpStatus.OK);
    }

    @GetMapping("/{musical-id}")
    public ResponseEntity getMusical(@PathVariable("musical-id") @Positive Long musicalId) {
        Musical response = musicalService.findMusical(musicalId);
        Theater responseTheater = theaterService.getTheater(response.getTheater().getTheaterId());
        return new ResponseEntity<>(
                new MappingResponseDto<>(musicalMapper.musicalToMusicalResponseDto(response),
                        theaterMapper.theaterToMusicalResponse(responseTheater)), HttpStatus.OK);
    }

    @GetMapping("/{musical-id}/actors")
    public ResponseEntity getActors(@PathVariable("musical-id") @Positive Long musicalId) {
        Musical musical = musicalService.findMusical(musicalId);
        List<Response> actorMusicals = actorMusicalService.getMusicalActors(musicalId);
        return new ResponseEntity<>(
                new MappingActorResponseDto(musicalMapper.musicalToActorMusicalResponseDto(musical),
                        actorMusicals), HttpStatus.OK);
    }

    @GetMapping("/{musical-id}/board")
    public ResponseEntity getBoards(@PathVariable("musical-id") @Positive Long musicalId) {
        List<Board> responseBoards = musicalService.findMusicalBoards(musicalId);
        Category responseCategory = musicalService.findCategoryName(musicalId);
        return new ResponseEntity<>(musicalMapper.boardsToMusicalResponseDtos(musicalId,
                boardMapper.boardsToMusicals(responseBoards),
                responseCategory), HttpStatus.OK);
    }

}
