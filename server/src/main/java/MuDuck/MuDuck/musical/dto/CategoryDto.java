package MuDuck.MuDuck.musical.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CategoryDto {
    @Getter
    @AllArgsConstructor
    public static class Response{
        private String categoryName;
    }
}
