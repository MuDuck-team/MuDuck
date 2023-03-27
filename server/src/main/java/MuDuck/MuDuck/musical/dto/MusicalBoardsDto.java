package MuDuck.MuDuck.musical.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MusicalBoardsDto {
    @Getter
    @AllArgsConstructor
    public static class Response{
        private String title;
        private String nickname;
        private String createdAt;
        int views;
        int likes;
        private String categoryName;
    }

}
