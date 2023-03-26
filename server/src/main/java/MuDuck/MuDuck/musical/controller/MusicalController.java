package MuDuck.MuDuck.musical.controller;

import MuDuck.MuDuck.actor.dto.ActorDto;
import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.actor.mapper.ActorMapper;
import MuDuck.MuDuck.actor.service.ActorService;
import MuDuck.MuDuck.actorMusical.mapper.ActorMusicalMapper;
import MuDuck.MuDuck.actorMusical.service.ActorMusicalService;
import MuDuck.MuDuck.musical.dto.ActorMusicalDto.MappingActorResponseDto;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.actorMusical.repository.ActorMusicalRepository;
import MuDuck.MuDuck.musical.dto.MusicalDto.MappingResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.MultiResponseDto;
import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.MusicalBoards;
import MuDuck.MuDuck.musical.mapper.MusicalMapper;
import MuDuck.MuDuck.musical.repository.MusicalRepository;
import MuDuck.MuDuck.musical.service.MusicalService;
import MuDuck.MuDuck.theater.entitiy.Theater;
import MuDuck.MuDuck.theater.mapper.TheaterMapper;
import MuDuck.MuDuck.theater.service.TheaterService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    private final MusicalRepository musicalRepository;
    private final TheaterService theaterService;
    private final TheaterMapper theaterMapper;
    private final ActorMusicalService actorMusicalService;
    private final ActorMusicalMapper actorMusicalMapper;
    private final ActorService actorService;
    private final ActorMapper actorMapper;

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
    public ResponseEntity getMusicalFilters(@RequestParam(defaultValue = "openDate") String sort,
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

    @GetMapping("/{musical-id}")
    public ResponseEntity getMusical(@PathVariable("musical-id") @Positive Long musicalId){
        Musical response = musicalService.findMusical(musicalId);
        Theater responseTheater = theaterService.getTheater(response.getTheater().getTheaterId());
        return new ResponseEntity<>(new MappingResponseDto<>(musicalMapper.musicalToMusicalResponseDto(response),theaterMapper.theaterToMusicalResponse(responseTheater)),HttpStatus.OK);
    }

    @GetMapping("/{musical-id}/actors")
    public ResponseEntity getActors(@PathVariable("musical-id") @Positive Long musicalId){
//        Musical musical = musicalService.findMusical(musicalId);
//        List<MappingActorResponseDto> responseActors = musicalService.findMusialActors(musicalId);
//        return new ResponseEntity<>(actorMusicalMapper.actorsToMusicalResponseDtos(musical, responseActors), HttpStatus.OK);
        //actormusical에 정보 들어가는지 확인
        ActorMusical actorMusical = actorMusicalService.findActorMusical(musicalId);
        Actor actor = actorService.findActor(actorMusical.getActor().getActorId());
        return new ResponseEntity<>(new MappingActorResponseDto<>(actorMusicalMapper.actorsToMusicalDto(actorMusical),actorMapper.actorToActorMusicalResponse(actor)),HttpStatus.OK);
    }

    @GetMapping("/{musical-id}/board")
    public ResponseEntity getBoards(@PathVariable("musical-id") @Positive Long musicalId){
        Musical musical = musicalService.findMusical(musicalId);
        List<MusicalBoards> responseBoards = musicalService.findMusicalBoards(musicalId);
        Category responseCategory = musicalService.findCategoryName(musicalId);
        return new ResponseEntity<>(musicalMapper.boardsToMusicalResponseDtos(musical, responseBoards,
                responseCategory), HttpStatus.OK);
    }

}
