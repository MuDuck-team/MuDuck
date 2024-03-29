package MuDuck.MuDuck.member.service;

import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.repository.MemberRepository;
import MuDuck.MuDuck.utils.CustomBeanUtils;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final CustomBeanUtils<Member> beanUtils;

    public MemberService(MemberRepository memberRepository, CustomBeanUtils<Member> beanUtils) {
        this.memberRepository = memberRepository;
        this.beanUtils = beanUtils;
    }

    public Member createMember(Member member) {
        verifyExistEmail(member);

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    public Member updateMember(Member member) {
        // 닉네임 중복 체크
        verifyExistNickName(member.getNickName());

        Member findedMember = findVerifiedMember(member.getMemberId());

        Member updatedMember = beanUtils.copyNonNullProperties(member, findedMember);

        return memberRepository.save(updatedMember);
    }

    public Member getMember(long memberId){

        return findVerifiedMember(memberId);

    }

    private Member findVerifiedMember(long memberId) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    private  Member findVerifiedMemberByUserName(String userName){
        Optional<Member> optionalMember = memberRepository.findByNickName(userName);
        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistEmail(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }

    public Member findByEmail(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistNickName(String nickName){

        Optional<Member> optionalMember = memberRepository.findByNickName(nickName);
        if(optionalMember.isPresent()){
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_NICKNAME);
        }

    }
}
