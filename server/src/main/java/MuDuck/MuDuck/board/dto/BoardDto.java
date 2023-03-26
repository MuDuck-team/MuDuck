package MuDuck.MuDuck.board.dto;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.boardCategory.entity.BoardCategory;
import MuDuck.MuDuck.member.entity.Member;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Access;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BoardDto {

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Builder
    public static class Post{
        @NotEmpty(message = "카테고리 아이디 값이 적어도 1개 있어야 합니다.")
        private List<Long> categoryIds; // 카테고리 식별자가 들어있다.
        @NotBlank(message = "게시글의 제목 값이 없습니다.")
        private String title;
        @NotBlank(message = "게시글 본문 값이 없습니다.")
        private String content;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    public static class Patch{
        private String title;

        private String content;
    }

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

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Builder
    public static class BoardContentResponse{
        private long id;
        private BoardContentHead head;
        private BoardContentBody body;
        private boolean liked;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Builder
    public static class BoardContentHead{
        private long memberId;
        private String userProfile;
        private String nickname;
        private String createdAt;
        private int view;
        private int like;
        private int totalComment;
        private String category;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Builder
    public static class BoardContentBody{
        private String title;
        private String content;
    }
}
