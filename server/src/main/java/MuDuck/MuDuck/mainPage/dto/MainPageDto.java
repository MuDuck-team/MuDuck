package MuDuck.MuDuck.mainPage.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainPageDto {
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    @Getter
    public static class postsResponse {
        private long id;
        private String userProfile;
        private String nickName;
        private String createdAt;
        private String title;
        private int viewCount;
        private int commentsCount;
        private int likeCount;
    }
}
