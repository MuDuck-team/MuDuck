package MuDuck.MuDuck.comment.mapper;

import MuDuck.MuDuck.comment.dto.CommentDto;
import MuDuck.MuDuck.comment.dto.CommentDto.CommentsHead;
import MuDuck.MuDuck.comment.dto.CommentDto.Response;
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.comment.entity.Comment.CommentStatus;
import MuDuck.MuDuck.member.entity.Member;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentPostDtoToComment(CommentDto.Post post);

    default CommentDto.Response commentToCommentResponseDto(Member member, Comment comment) {

        CommentDto.Response response = Response.builder()
                .id(comment.getCommentId())
                .head(CommentsHead.builder()
                        .memberId(member.getMemberId())
                        .userProfile(member.getPicture())
                        .nickname(member.getNickName())
                        .createdAt(comment.getCreatedAt()
                                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                        .build())
                .body(comment.getCommentStatus() == CommentStatus.COMMENT_POST ? comment.getBody() : "삭제된 댓글입니다.")
                .commentStatus(comment.getCommentStatus().getStatus())
                .parentId(comment.getParent() == null ? null : comment.getParent().getCommentId())
                .comments(comment.getChildren() == null ? new ArrayList<>()
                        : commentsToCommentResponseDtos(comment.getChildren()))
                .build();

        return response;
    }

    default List<CommentDto.Response> commentsToCommentResponseDtos(List<Comment> comments) {
        return comments
                .stream()
                .map(comment -> commentToCommentResponseDto(comment.getMember(), comment))
                .collect(Collectors.toList());
    }
}
