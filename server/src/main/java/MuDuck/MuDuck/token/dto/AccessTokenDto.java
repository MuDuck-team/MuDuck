package MuDuck.MuDuck.token.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class AccessTokenDto {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class Response{
        private String token;

        private int status;
    }
}
