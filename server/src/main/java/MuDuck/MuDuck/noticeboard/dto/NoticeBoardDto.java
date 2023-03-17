package MuDuck.MuDuck.noticeboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class NoticeBoardDto {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Response{
        private Long id;
        private String lastCreatedAt;
        private String title;
    }
}
