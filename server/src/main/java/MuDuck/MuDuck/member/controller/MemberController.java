package MuDuck.MuDuck.member.controller;

import MuDuck.MuDuck.member.dto.MemberDto;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.mapper.MemberMapper;
import MuDuck.MuDuck.member.service.MemberService;
import java.security.Principal;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;

    private final MemberMapper memberMapper;

    @GetMapping("/my-info")
    public ResponseEntity getMyInfo(Principal principal){

        String username = principal.getName();

        Member findMember = memberService.findByEmail(username);

        MemberDto.Response response = memberMapper.memberToResponseDto(findMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId){

        Member member = memberService.getMember(memberId);

        MemberDto.Response response = memberMapper.memberToResponseDto(member);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
