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
import MuDuck.MuDuck.comment.dto.CommentDto;
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.comment.mapper.CommentMapper;
import MuDuck.MuDuck.comment.service.CommentService;
import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/boards")
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

    @PostMapping
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

        return new ResponseEntity<>(Map.of("boardId", createdBoard.getBoardId()), HttpStatus.CREATED);
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

        boardService.addView(board);

        return new ResponseEntity<>(new BoardContentMultipleResponse(
                boardMapper.multiInfoToBoardContentResponse(boardWriter, board, category, isLiked),
                commentMapper.commentsToCommentResponseDtos(onlyComment)), HttpStatus.OK);
    }

    @GetMapping("/category")
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

        boardService.updateBoard(board, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
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

    @PostMapping("/{board-id}/comments")
    public ResponseEntity postComment(@Positive @PathVariable("board-id") long boardId,
            @Valid @RequestBody CommentDto.Post requestBody, Principal principal) {
        // 대댓글 등록 구현부터 해놓고 생각해보기 - 대댓글 등록 함수 사용하면 될 것 같다는 생각이 들었다. => 따로 구현하자 commentId를 null로 입력하는건 좋지 않을 것 같다.
        // postComment() 때는 null이지만 이것 때문에 허용하면 postReply에 null을 넣는 사태를 막을 수 없다.

        // 댓글을 누가 작성했는지 파악해야 함
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        // 어느 게시물에 작성하는지도 파악해야 함
        Board board = boardService.findBoard(boardId);
        String category = boardService.findCategory(board);
        boolean isLike = boardService.isLiked(member);

        Comment comment = commentMapper.commentPostDtoToComment(requestBody);
        comment.setMember(member);
        comment.setBoard(board);

        Comment createdComment = commentService.createComment(comment);

        Board updatedBoard = boardService.findBoard(boardId);
        List<Comment> onlyComment = commentService.getCommentWithoutReply(
                updatedBoard.getComments());

        return new ResponseEntity<>(new BoardContentMultipleResponse(
                boardMapper.multiInfoToBoardContentResponse(member, board, category, isLike),
                commentMapper.commentsToCommentResponseDtos(onlyComment)
        ), HttpStatus.CREATED);
    }

    @PostMapping("/{board-id}/comments/{comment-id}")
    public ResponseEntity postReply(@Positive @PathVariable("board-id") long boardId,
            @Positive @PathVariable("comment-id") long commentId,
            @Valid @RequestBody CommentDto.Post requestBody, Principal principal) {
        // 댓글을 누가 작성했는지 파악해야 함
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        // 어느 게시물에 작성하는지도 파악해야 함
        Board board = boardService.findBoard(boardId);
        String category = boardService.findCategory(board);
        boolean isLike = boardService.isLiked(member);

        // 어느 부모 댓글에 다는 대댓글인지도 파악해야 함
        Comment parentComment = commentService.findComment(commentId);

        if(parentComment.getParent() != null){ // 대댓글을 달려는 댓글의 부모가 null이 아니라면 대댓글에 대댓글을 게시하려고 시도 중이니 Exception 처리
            throw new BusinessLogicException(ExceptionCode.INVALID_COMMENT);
        }

        Comment comment = commentMapper.commentPostDtoToComment(requestBody);
        comment.setMember(member);
        comment.setBoard(board);
        comment.setParent(parentComment);

        Comment createdComment = commentService.createComment(comment);

        Board updatedBoard = boardService.findBoard(boardId);
        List<Comment> onlyComment = commentService.getCommentWithoutReply(
                updatedBoard.getComments());

        return new ResponseEntity<>(new BoardContentMultipleResponse(
                boardMapper.multiInfoToBoardContentResponse(member, board, category, isLike),
                commentMapper.commentsToCommentResponseDtos(onlyComment)
        ), HttpStatus.CREATED);
    }

    @DeleteMapping("/{board-id}/comments/{comment-id}")
    public ResponseEntity deleteComment(@Positive @PathVariable("board-id") long boardId,
            @Positive @PathVariable("comment-id") long commentId, Principal principal) {
        // 요청자의 신분을 확인
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        commentService.deleteComment(commentId, member);

        // 어느 게시물에 작성하는지도 파악해야 함
        Board board = boardService.findBoard(boardId);
        String category = boardService.findCategory(board);
        boolean isLike = boardService.isLiked(member);

        Board updatedBoard = boardService.findBoard(boardId);
        List<Comment> onlyComment = commentService.getCommentWithoutReply(
                updatedBoard.getComments());

        return new ResponseEntity<>(new BoardContentMultipleResponse(
                boardMapper.multiInfoToBoardContentResponse(member, board, category, isLike),
                commentMapper.commentsToCommentResponseDtos(onlyComment)
        ), HttpStatus.CREATED);
    }
}
