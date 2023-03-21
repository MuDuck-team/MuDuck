package MuDuck.MuDuck.board.dto;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.boardCategory.entity.BoardCategory;
import MuDuck.MuDuck.member.entity.Member;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AccessLevel;
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
        private String userProfile;
        private String nickname;
        private String createdAt;
        private int view;
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
