package MuDuck.MuDuck.member.mapper;

import MuDuck.MuDuck.member.dto.MemberDto;
import MuDuck.MuDuck.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(source = "memberId", target = "id")
    @Mapping(source = "picture", target = "profileImageUrl")
    @Mapping(source = "memberRole.role", target = "role")
    @Mapping(source = "nickName", target = "nickname")
    MemberDto.Response memberToResponseDto(Member member);
}
