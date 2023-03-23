package MuDuck.MuDuck.board.controller;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.mapper.BoardMapper;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.boardCategory.entity.BoardCategory;
import MuDuck.MuDuck.boardCategory.service.BoardCategoryService;
import MuDuck.MuDuck.category.entity.Category;
import MuDuck.MuDuck.category.mapper.CategoryMapper;
import MuDuck.MuDuck.category.service.CategoryService;
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.comment.mapper.CommentMapper;
import MuDuck.MuDuck.comment.service.CommentService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import MuDuck.MuDuck.noticeboard.mapper.NoticeBoardMapper;
import MuDuck.MuDuck.noticeboard.service.NoticeBoardService;
import MuDuck.MuDuck.response.BoardContentMultipleResponse;
import MuDuck.MuDuck.response.BoardMultipleResponse;
import MuDuck.MuDuck.response.CategoryMultipleResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Validated
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    private final NoticeBoardService noticeBoardService;
    private final NoticeBoardMapper noticeBoardMapper;

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    private final MemberService memberService;

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    private final BoardCategoryService boardCategoryService;

    @PostMapping("/writing")
    public ResponseEntity postBoard(@Valid @RequestBody BoardDto.Post requestBody,
            Principal principal) {
        // 로그인 되어 있는 유저 이메일 받아오기.
        // 글 작성은 반드시 로그인 상태여야하므로 null인 상황은 고려하지 않는다. (SecurityConfiguration에서 걸러줄 예정)
        String email = principal.getName();
        Member member = memberService.findByEmail(email); // 글 작성자이자 로그인한 유저

        Board board = boardMapper.boardPostToBoard(requestBody, member);

        List<Long> categoryIds = boardMapper.boardPostToCategoryIds(requestBody);
        List<BoardCategory> boardCategories = boardCategoryService.getBoardCategories(categoryIds,
                board);
        board.setBoardCategories(boardCategories);

        Board createdBoard = boardService.createBoard(board);

        createdBoard.setBoardCategories(boardCategories);
        String category = boardService.findCategory(createdBoard);

        return new ResponseEntity<>(new BoardContentMultipleResponse(
                boardMapper.multiInfoToBoardContentResponse(member, createdBoard, category, false),
                commentMapper.commentsToCommentResponseDtos(new ArrayList<>())),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getBoards(
            @Positive @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "recent") String sortBy,
            @RequestParam(required = false, defaultValue = "전체") String categoryName) {
        // categoryName이 주어진 값 이외의 것을 넣으면 Exception 발생시키는 코드 추가해야함

        Page<Board> pageBoards = boardService.findBoards(page - 1, sortBy, categoryName);
        List<Board> boards = pageBoards.getContent();
        List<NoticeBoard> noticeBoards = noticeBoardService.getTopNoticeBoard();
        List<Category> categories = categoryService.findCategories();

        return new ResponseEntity<>(new BoardMultipleResponse(
                noticeBoardMapper.noticeBoardsToNoticeBoardResponseDtos(noticeBoards),
                boardMapper.boardsToBoardResponseDtos(boards), pageBoards,
                categoryMapper.categoriesToCategoryResponseDtos(categories)), HttpStatus.OK);

    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoardContent(@Positive @PathVariable("board-id") long boardId,
            Principal principal) {
        Board board = boardService.findBoard(boardId);
        Member boardWriter = board.getMember();
        String category = boardService.findCategory(board);

        // principal.getName은 유저의 이메일을 반환, 비로그인인 경우는 principal이 null로 반환됨
        boolean isLiked;
        if (principal == null) { // 비회원인 경우
            isLiked = false;
        } else { // 회원인 경우
            String email = principal.getName();
            Member member = memberService.findByEmail(email);
            isLiked = boardService.isLiked(member);
        }

        List<Comment> onlyComment = commentService.getCommentWithoutReply(board.getComments());

        return new ResponseEntity<>(new BoardContentMultipleResponse(
                boardMapper.multiInfoToBoardContentResponse(boardWriter, board, category, isLiked),
                commentMapper.commentsToCommentResponseDtos(onlyComment)), HttpStatus.OK);
    }

    @GetMapping("/writing")
    public ResponseEntity getCategoryList() {
        List<Category> categories = categoryService.findCategories();

        return new ResponseEntity<>(new CategoryMultipleResponse(
                categoryMapper.categoriesToCategoryResponseDtos(categories)), HttpStatus.OK);
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity patchBoard(@Positive @PathVariable("board-id") long boardId,
            @Valid @RequestBody BoardDto.Patch requestBody, Principal principal) {
        // 로그인 되어 있는 유저 이메일 받아오기.
        // 글 수정은 반드시 로그인 상태여야하므로 null인 상황은 고려하지 않는다. (SecurityConfiguration에서 걸러줄 예정)
        String email = principal.getName();
        Member member = memberService.findByEmail(email); // 로그인한 유저

        Board board = boardMapper.boardPatchToBoard(requestBody, boardId);

        Board updatedBoard = boardService.updateBoard(board, member.getMemberId());

        List<Comment> onlyComment = commentService.getCommentWithoutReply(updatedBoard.getComments());
        String category = boardService.findCategory(updatedBoard);
        boolean isLiked = boardService.isLiked(member);

        return new ResponseEntity<>(new BoardContentMultipleResponse(
                boardMapper.multiInfoToBoardContentResponse(member, updatedBoard, category, isLiked),
                commentMapper.commentsToCommentResponseDtos(onlyComment)), HttpStatus.OK);
    }

    @DeleteMapping("/{board-id}")
    public ResponseEntity deleteBoard(@Positive @PathVariable("board-id") long boardId,
            Principal principal) {
        // 로그인 되어 있는 유저 이메일 받아오기.
        // 글 삭제는 반드시 로그인 상태여야하므로 null인 상황은 고려하지 않는다. (SecurityConfiguration에서 걸러줄 예정)
        String email = principal.getName();
        Member member = memberService.findByEmail(email); // 로그인한 유저

        boardService.deleteBoard(boardId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
