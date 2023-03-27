package MuDuck.MuDuck.mainPage.controller;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.mainPage.mapper.MainPageMapper;
import MuDuck.MuDuck.musical.dto.MusicalDto.SingleResponseDto;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.mapper.MusicalMapper;
import MuDuck.MuDuck.musical.service.MusicalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main-page")
@RequiredArgsConstructor
@Slf4j
public class MainPageController {

    private final BoardService boardService;
    private final MusicalService musicalService;
    private final MusicalMapper musicalMapper;
    private final MainPageMapper mainPageMapper;

    @GetMapping("/daily")
    public ResponseEntity getDailyPosts() {
        List<Board> boards = boardService.getDailyPopularPosts();

        return new ResponseEntity<>(mainPageMapper.boardsToMainPostsReponses(boards), HttpStatus.OK);
    }

    @GetMapping("/weekly")
    public ResponseEntity getWeeklyPosts() {
        List<Board> boards = boardService.getWeeklyPopularPosts();

        return new ResponseEntity<>(mainPageMapper.boardsToMainPostsReponses(boards), HttpStatus.OK);
    }

    @GetMapping("/musicals")
    public ResponseEntity getRecommendMusicals(){
        List<Musical> musicals = musicalService.findRecommendMusicals();

        return new ResponseEntity<>(new SingleResponseDto<>(musicalMapper.musicalsToMusicalResponseDtos(musicals)), HttpStatus.OK);
    }
}
