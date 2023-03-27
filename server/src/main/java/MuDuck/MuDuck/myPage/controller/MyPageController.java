package MuDuck.MuDuck.myPage.controller;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.comment.service.CommentService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.myPage.mapper.MyPageMapper;
import MuDuck.MuDuck.response.MyPageBoardMultipleResponse;
import MuDuck.MuDuck.response.MyPageCommentsMultipleResponse;
import java.security.Principal;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("my-page")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;

    private final MyPageMapper myPageMapper;

    @GetMapping("/boards")
    public ResponseEntity getMyBoards(
            @Positive @RequestParam(required = false, defaultValue = "1") int page,
            @Positive @RequestParam int size, Principal principal) {
        // 요청자의 신분을 확인
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        Page<Board> boards = boardService.getMyBoards(member, page - 1, size);

        return new ResponseEntity<>(new MyPageBoardMultipleResponse(
                myPageMapper.boardsToMyPageResponseDtos(boards.getContent()), boards),
                HttpStatus.OK);
    }

    @GetMapping("/liked-boards")
    public ResponseEntity getMyLikedBoards(
            @Positive @RequestParam(required = false, defaultValue = "1") int page,
            @Positive @RequestParam int size, Principal principal) {
        // 요청자의 신분을 확인
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        Page<Board> likedBoards = boardService.getMyLikedBoards(member, page - 1, size);

        return new ResponseEntity<>(new MyPageBoardMultipleResponse(
                myPageMapper.boardsToMyPageResponseDtos(likedBoards.getContent()), likedBoards),
                HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity getMyComments(
            @Positive @RequestParam(required = false, defaultValue = "1") int page,
            @Positive @RequestParam int size, Principal principal) {
        // 요청자의 신분을 확인
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        Page<Comment> comments = commentService.getMyComments(member, page - 1, size);

        return new ResponseEntity<>(new MyPageCommentsMultipleResponse(
                myPageMapper.commentsToMyPageCommentsResponseDtos(comments.getContent()), comments),
                HttpStatus.OK);
    }
}
