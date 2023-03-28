package MuDuck.MuDuck.response;


import MuDuck.MuDuck.myPage.dto.MyPageDto;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class MyPageCommentsMultipleResponse {
    private List<MyPageDto.commentsResponse> comments;
    private PageInfo pageInfo;

    public MyPageCommentsMultipleResponse(List<MyPageDto.commentsResponse> comments, Page page) {
        this.comments = comments;
        this.pageInfo = new PageInfo(page.getNumber() + 1, page.getSize(), page.getTotalElements(),
                page.getTotalPages());
    }
}
