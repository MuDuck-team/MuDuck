package MuDuck.MuDuck.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardDto {

    @Getter
    @Builder
    public static class Response{
        private Long id;
        private Long memberId;
        private String nickname;
        private String lastCreatedAt;
        private String userProfile;
        private String title;
        private Integer view;
        private Integer commentCount;
        private Integer boardLike;
    }
}
