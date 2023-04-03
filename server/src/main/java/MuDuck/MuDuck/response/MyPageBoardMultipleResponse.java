package MuDuck.MuDuck.response;

import MuDuck.MuDuck.myPage.dto.MyPageDto.Response;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class MyPageBoardMultipleResponse {

    private List<Response> boards;
    private PageInfo pageInfo;

    public MyPageBoardMultipleResponse(List<Response> boards, Page page) {
        this.boards = boards;
        this.pageInfo = new PageInfo(page.getNumber() + 1, page.getSize(), page.getTotalElements(),
                page.getTotalPages());
    }
}
