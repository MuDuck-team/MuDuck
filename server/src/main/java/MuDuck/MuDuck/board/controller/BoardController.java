package MuDuck.MuDuck.board.controller;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.mapper.BoardMapper;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import MuDuck.MuDuck.noticeboard.mapper.NoticeBoardMapper;
import MuDuck.MuDuck.noticeboard.service.NoticeBoardService;
import MuDuck.MuDuck.response.BoardMultipleResponse;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@Validated
@Slf4j
public class BoardController {

    private final static int SIZE = 8;

    private BoardService boardService;
    private BoardMapper boardMapper;

    private NoticeBoardService noticeBoardService;
    private NoticeBoardMapper noticeBoardMapper;

    public BoardController(BoardService boardService, BoardMapper boardMapper,
            NoticeBoardService noticeBoardService, NoticeBoardMapper noticeBoardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.noticeBoardService = noticeBoardService;
        this.noticeBoardMapper = noticeBoardMapper;
    }

    @GetMapping
    public ResponseEntity getBoards(@Positive @RequestParam int page) {
        Page<Board> pageBoards = boardService.findBoards(page - 1, SIZE);
        List<Board> boards = pageBoards.getContent();
        List<NoticeBoard> noticeBoards = noticeBoardService.getTopNoticeBoard();

        return new ResponseEntity<>(new BoardMultipleResponse(
                noticeBoardMapper.noticeBoardsToNoticeBoardResponseDtos(noticeBoards),
                boardMapper.boardsToBoardResponseDtos(boards), pageBoards), HttpStatus.OK);

    }
}
