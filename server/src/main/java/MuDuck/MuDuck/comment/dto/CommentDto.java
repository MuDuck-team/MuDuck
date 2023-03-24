package MuDuck.MuDuck.comment.dto;

import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.member.entity.Member;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommentDto {

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    public static class Post{
        @NotEmpty(message = "댓글 내용은 반드시 값이 존재해야합니다.")
        private String body;
    }

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
        private long memberId;
        private String userProfile;
        private String nickname;
        private String createdAt;
    }
}
