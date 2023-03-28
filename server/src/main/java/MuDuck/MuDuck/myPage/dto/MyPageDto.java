package MuDuck.MuDuck.myPage.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPageDto {
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    @Getter
    public static class Response {
        private long id;
        private String title;
        private String createdAt;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    @Getter
    public static class commentsResponse {
        private long id;
        private long boardId;
        private String body;
        private String createdAt;
    }
}
