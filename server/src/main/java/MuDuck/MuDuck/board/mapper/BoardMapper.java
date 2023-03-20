package MuDuck.MuDuck.board.mapper;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.utils.Chrono;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    default BoardDto.Response boardToBoardResponseDto(Board board) {
        Member member = board.getMember();
        BoardDto.Response response = BoardDto.Response.builder()
                .id(board.getBoardId())
                .memberId(member.getMemberId())
                .nickname(member.getNickName())
                .userProfile(member.getPicture())
                .lastCreatedAt(Chrono.timesAgo(board.getCreatedAt()))
                .title(board.getTitle())
                .view(board.getViews())
                .commentCount(board.getComments().size())
                .boardLike(board.getLikes())
                .build();
        return response;
    }

    default List<BoardDto.Response> boardsToBoardResponseDtos(List<Board> boards) {
        return boards.stream().map(board -> boardToBoardResponseDto(board)).collect(Collectors.toList());
    }
}
