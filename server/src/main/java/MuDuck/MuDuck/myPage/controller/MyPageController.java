package MuDuck.MuDuck.myPage.controller;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.boardLike.entity.BoardLike;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.myPage.mapper.MyPageMapper;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("my-page")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final BoardService boardService;
    private final MemberService memberService;

    private final MyPageMapper myPageMapper;

    @GetMapping("/boards")
    public ResponseEntity getMyBoards(Principal principal){
        // 요청자의 신분을 확인
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        List<Board> boards = boardService.getMyBoards(member);

        return new ResponseEntity<>(myPageMapper.boardsToMyPageResponseDtos(boards), HttpStatus.OK);
    }

    @GetMapping("/liked-boards")
    public ResponseEntity getMyLikedBoards(Principal principal){
        // 요청자의 신분을 확인
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        List<Board> likedBoards = boardService.getMyLikedBoards(member);

        return new ResponseEntity<>(myPageMapper.boardsToMyPageResponseDtos(likedBoards), HttpStatus.OK);
    }
}
