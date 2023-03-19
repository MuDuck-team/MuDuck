package MuDuck.MuDuck.response;

import MuDuck.MuDuck.category.dto.CategoryDto.Response;
import java.util.List;
import lombok.Getter;

@Getter
public class CategoryMultipleResponse {
    private List<Response> category;
    private List<Response> mentionedMusical;
}
