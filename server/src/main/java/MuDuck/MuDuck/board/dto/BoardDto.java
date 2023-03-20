package MuDuck.MuDuck.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardDto {

    @AllArgsConstructor
    @Getter
    @Builder
    public static class Response{
        private long id;
        private long memberId;
        private String nickname;
        private String lastCreatedAt;
        private String userProfile;
        private String title;
        private int view;
        private int commentCount;
        private int boardLike;
    }
}
