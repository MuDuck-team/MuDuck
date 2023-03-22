package MuDuck.MuDuck.comment.dto;

import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.member.entity.Member;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentDto {

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Builder
    public static class Response{
        private long id;
        private CommentsHead head;
        private String body;
        private Long parentId;
        List<Response> comments;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Builder
    public static class CommentsHead{
        private String userProfile;
        private String nickname;
        private String createdAt;
    }
}
