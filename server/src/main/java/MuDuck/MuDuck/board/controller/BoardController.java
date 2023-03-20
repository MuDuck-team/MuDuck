package MuDuck.MuDuck.board.controller;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.mapper.BoardMapper;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.category.entity.Category;
import MuDuck.MuDuck.category.mapper.CategoryMapper;
import MuDuck.MuDuck.category.service.CategoryService;
import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import MuDuck.MuDuck.noticeboard.mapper.NoticeBoardMapper;
import MuDuck.MuDuck.noticeboard.service.NoticeBoardService;
import MuDuck.MuDuck.response.BoardMultipleResponse;
import MuDuck.MuDuck.response.CategoryMultipleResponse;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Validated
@Slf4j
public class BoardController {

    private final static int SIZE = 8;

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    private final NoticeBoardService noticeBoardService;
    private final NoticeBoardMapper noticeBoardMapper;

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity getBoards(@Positive @RequestParam int page) {
        Page<Board> pageBoards = boardService.findBoards(page - 1, SIZE);
        List<Board> boards = pageBoards.getContent();
        List<NoticeBoard> noticeBoards = noticeBoardService.getTopNoticeBoard();
        List<Category> categories = categoryService.findCategories();

        return new ResponseEntity<>(new BoardMultipleResponse(
                noticeBoardMapper.noticeBoardsToNoticeBoardResponseDtos(noticeBoards),
                boardMapper.boardsToBoardResponseDtos(boards), pageBoards, categoryMapper.categoriesToCategoryResponseDtos(categories)), HttpStatus.OK);

    }

    @GetMapping("/writing")
    public ResponseEntity getCategoryList() {
        List<Category> categories = categoryService.findCategories();

        return new ResponseEntity<>(new CategoryMultipleResponse(categoryMapper.categoriesToCategoryResponseDtos(categories)), HttpStatus.OK);
    }
}
