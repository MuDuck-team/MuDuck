package MuDuck.MuDuck.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberDto {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class Response{
        private long id;
        private String nickname;
        private String profileImageUrl;
        private String role;
    }

}
