package MuDuck.MuDuck.response;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.category.dto.CategoryDto;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BoardMultipleResponse {
    private List<NoticeBoardDto.Response> noticeBoards;
    private List<BoardDto.Response> boards;
    private PageInfo pageInfo;
    private List<CategoryDto.Response> categoryList;

    public BoardMultipleResponse(List<Response> noticeBoardResponse,
            List<BoardDto.Response> boardResponse, Page page, List<CategoryDto.Response> categories) {

        List<CategoryDto.Response> categoryList = new ArrayList<>();

        for(CategoryDto.Response response : categories){
            if(response.getParentId() == null){
                categoryList.add(response);
            }
        }

        this.noticeBoards = noticeBoardResponse;
        this.boards = boardResponse;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.categoryList = categoryList;
    }
}
