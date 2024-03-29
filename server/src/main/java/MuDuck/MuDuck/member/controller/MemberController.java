package MuDuck.MuDuck.member.controller;

import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.member.dto.MemberDto;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.mapper.MemberMapper;
import MuDuck.MuDuck.member.service.MemberService;
import java.security.Principal;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;

    private final MemberMapper memberMapper;

    @GetMapping("/my-info")
    public ResponseEntity getMyInfo(Principal principal){
        // accessToken 없이 접근이 불가하기에 null 상황은 고려하지 않는다.
        String username = principal.getName();

        Member findMember = memberService.findByEmail(username);

        MemberDto.Response response = memberMapper.memberToResponseDto(findMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMemberToMemberId(@PathVariable("member-id") @Positive long memberId,
            Principal principal){

        Member member = memberService.getMember(memberId);

        // 해당 유저가 로그인한 유저인지 확인하기
        if(!member.getEmail().equals(principal.getName())){
            throw new BusinessLogicException(ExceptionCode.NOT_SAME_USER);
        }

        MemberDto.Response response = memberMapper.memberToResponseDto(member);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
            @RequestBody @Valid MemberDto.Patch patch, Principal principal){

        log.info("넘어온 memberId : {}", memberId);

        Member member = memberService.getMember(memberId);

        // 해당 유저가 로그인한 유저인지 확인
        if(!member.getEmail().equals(principal.getName())){
            throw new BusinessLogicException(ExceptionCode.NOT_SAME_USER);
        }

        Member patchMember = memberMapper.memberPatchDtoToMember(patch);

        patchMember.setMemberId(memberId);

        Member updateMember = memberService.updateMember(patchMember);

        MemberDto.Response response = memberMapper.memberToResponseDto(updateMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
