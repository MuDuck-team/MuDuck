package MuDuck.MuDuck.response;

import MuDuck.MuDuck.category.dto.CategoryDto.Response;
import MuDuck.MuDuck.category.entity.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CategoryMultipleResponse {
    private List<Response> category;
    private List<Response> mentionedMusical;

    public CategoryMultipleResponse(List<Response> categories){
        List<Response> category = new ArrayList<>();
        List<Response> mentionedMusical = new ArrayList<>();

        for(Response response : categories){
            if(response.getParentId() == null){
                category.add(response);
            }else{
                mentionedMusical.add(response);
            }
        }

        this.category = category;
        this.mentionedMusical = mentionedMusical;
    }
}
