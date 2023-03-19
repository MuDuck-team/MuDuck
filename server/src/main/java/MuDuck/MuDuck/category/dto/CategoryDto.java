package MuDuck.MuDuck.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CategoryDto {
    @AllArgsConstructor
    @Getter
    public static class Response{
        private long id;
        private String categoryName;
        private Long parentId;
    }
}
