package MuDuck.MuDuck.noticeboard.mapper;

import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto.Response;
import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticeBoardMapper {
    default NoticeBoardDto.Response noticeBoardToNoticeBoardResponseDto(NoticeBoard noticeBoard){
        NoticeBoardDto.Response response = NoticeBoardDto.Response.builder()
                .id(noticeBoard.getNoticeBoardId())
                .lastCreatedAt(String.valueOf(noticeBoard.getCreatedAt()))
                .title(noticeBoard.getTitle())
                .build();
        return response;
    }

    default List<NoticeBoardDto.Response> noticeBoardsToNoticeBoardResponseDtos(List<NoticeBoard> noticeBoards){
        return noticeBoards.stream().map(noticeBoard -> noticeBoardToNoticeBoardResponseDto(noticeBoard)).collect(
                Collectors.toList());
    }
}
