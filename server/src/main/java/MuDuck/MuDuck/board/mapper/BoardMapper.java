package MuDuck.MuDuck.board.mapper;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentBody;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentHead;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentResponse;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.utils.Chrono;
import java.time.format.DateTimeFormatter;
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

    // 여기서의 Member는 글 작성자를 의미한다.
    default BoardDto.BoardContentResponse multiInfoToBoardContentResponse(Member member, Board board, String category, boolean isLiked){
        BoardDto.BoardContentResponse response = BoardContentResponse.builder()
                .id(board.getBoardId())
                .head(BoardContentHead.builder()
                        .userProfile(member.getPicture())
                        .nickname(member.getNickName())
                        .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                        .view(board.getViews())
                        .like(board.getLikes())
                        .totalComment(board.getComments().size())
                        .category(category)
                        .build())
                .body(BoardContentBody.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .liked(isLiked)
                .build();
        return response;
    }
}
