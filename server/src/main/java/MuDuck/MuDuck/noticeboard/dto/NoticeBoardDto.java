package MuDuck.MuDuck.noticeboard.dto;

import lombok.Builder;
import lombok.Getter;

public class NoticeBoardDto {
    @Getter
    @Builder
    public static class Response{
        private Long id;
        private String lastCreatedAt;
        private String title;
    }
}
