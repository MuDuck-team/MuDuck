package MuDuck.MuDuck.response;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto.Response;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BoardMultipleResponse {
    private List<NoticeBoardDto.Response> noticeBoardResponse;
    private List<BoardDto.Response> boardResponse;
    private PageInfo pageInfo;

    public BoardMultipleResponse(List<Response> noticeBoardResponse,
            List<BoardDto.Response> boardResponse, Page page) {
        this.noticeBoardResponse = noticeBoardResponse;
        this.boardResponse = boardResponse;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
