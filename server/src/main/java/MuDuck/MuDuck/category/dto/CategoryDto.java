package MuDuck.MuDuck.category.dto;

import MuDuck.MuDuck.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryDto {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Response{
        private long id;
        private String categoryName;
        private Long parentId;
    }
}
