package MuDuck.MuDuck.board.mapper;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.board.entity.Board;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    default BoardDto.Response boardToBoardResponseDto(Board board) {
        BoardDto.Response response = BoardDto.Response.builder()
                .id(board.getBoardId())
                .memberId(board.getMember().getMemberId())
                .nickname(board.getMember().getNickName())
                .userProfile(board.getMember().getPicture())
                .lastCreatedAt(String.valueOf(board.getCreatedAt()))
                .title(board.getTitle())
                .view(board.getViews())
                .commentCount(board.getComments().size())
                .boardLike(board.getLike())
                .build();
        return response;
    }

    default List<BoardDto.Response> boardsToBoardResponseDtos(List<Board> boards) {
        return boards.stream().map(board -> boardToBoardResponseDto(board)).collect(Collectors.toList());
    }
}
