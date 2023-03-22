package MuDuck.MuDuck.comment.mapper;

import MuDuck.MuDuck.comment.dto.CommentDto;
import MuDuck.MuDuck.comment.dto.CommentDto.CommentsHead;
import MuDuck.MuDuck.comment.dto.CommentDto.Response;
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.member.entity.Member;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    default CommentDto.Response commentToCommentResponseDto(Member member, Comment comment) {

        CommentDto.Response response = Response.builder()
                .id(comment.getCommentId())
                .head(CommentsHead.builder()
                        .userProfile(member.getPicture())
                        .nickname(member.getNickName())
                        .createdAt(comment.getCreatedAt()
                                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                        .build())
                .body(comment.getBody())
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
