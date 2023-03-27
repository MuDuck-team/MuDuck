package MuDuck.MuDuck.board.mapper;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentBody;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentHead;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentResponse;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.utils.Chrono;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    default Board boardPostToBoard(BoardDto.Post requestBody, Member member){
        Board board = Board.builder()
                .title(requestBody.getTitle())
                .content(requestBody.getContent())
                .member(member)
                .build();

        return board;
    }

    default Board boardPatchToBoard(BoardDto.Patch requestBody, long boardId){
        return Board.builder().boardId(boardId)
                .title(requestBody.getTitle())
                .content(requestBody.getContent())
                .build();
    }

    // id 리스트에 다 null로 담겨 오는 경우 때문에 체크해서 Exception 날려주는 상황이 필요하다.
    default List<Long> boardPostToCategoryIds(BoardDto.Post requestBody){
        List<Long> categoryIds = new ArrayList<>();
        for(Long id : requestBody.getCategoryIds()){
            if(id != null){
                categoryIds.add(id);
            }
        }
        return categoryIds;
    }

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
                .commentCount(board.getCommentsSize())
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
                        .memberId(member.getMemberId())
                        .userProfile(member.getPicture())
                        .nickname(member.getNickName())
                        .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                        .view(board.getViews())
                        .like(board.getLikes())
                        .totalComment(board.getCommentsSize()) // 삭제된 댓글 제외하고 개수 세기
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

    default BoardDto.BoardContentHead boardToBoardContentHead(Board board){
        Member member = board.getMember();
        BoardDto.BoardContentHead response = BoardContentHead.builder()
                .memberId(member.getMemberId())
                .userProfile(member.getPicture())
                .nickname(member.getNickName())
                .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .view(board.getViews())
                .like(board.getLikes())
                .totalComment(board.getCommentsSize())
                .build();
        return response;
    }

    List<BoardDto.BoardContentHead> boardsToBoardContentHead(List<Board> boards);
}
