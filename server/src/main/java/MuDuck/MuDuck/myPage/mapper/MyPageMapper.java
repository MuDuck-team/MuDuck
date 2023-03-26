package MuDuck.MuDuck.myPage.mapper;

import MuDuck.MuDuck.board.entity.Board;
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
}
