package MuDuck.MuDuck.myPage.mapper;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.myPage.dto.MyPageDto;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyPageMapper {
    default MyPageDto.Response boardToMyPageResponseDto(Board board){
        MyPageDto.Response response = MyPageDto.Response.builder()
                .id(board.getBoardId())
                .title(board.getTitle())
                .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
        return response;
    }

    List<MyPageDto.Response> boardsToMyPageResponseDtos(List<Board> boards);

    default MyPageDto.commentsResponse commentToMyPageCommentsResponseDto(Comment comment){
        MyPageDto.commentsResponse response = MyPageDto.commentsResponse.builder()
                .id(comment.getCommentId())
                .boardId(comment.getBoard().getBoardId())
                .body(comment.getBody())
                .createdAt(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
        return response;
    }

    List<MyPageDto.commentsResponse> commentsToMyPageCommentsResponseDtos(List<Comment> comments);
}
