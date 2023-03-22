package MuDuck.MuDuck.response;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.comment.dto.CommentDto;
import MuDuck.MuDuck.comment.dto.CommentDto.Response;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardContentMultipleResponse {
    private BoardDto.BoardContentResponse boardContent;
    private List<Response> comments;
}
