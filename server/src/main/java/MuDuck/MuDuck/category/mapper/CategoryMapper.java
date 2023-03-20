package MuDuck.MuDuck.category.mapper;

import MuDuck.MuDuck.category.dto.CategoryDto;
import MuDuck.MuDuck.category.entity.Category;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    default CategoryDto.Response categoryToCategoryResponseDto(Category category) {
        CategoryDto.Response response = CategoryDto.Response.builder().id(category.getCategoryId())
                .categoryName(category.getCategoryName()).
                parentId(category.getParent()==null ? null : category.getParent().getCategoryId()).build();

        return response;
    }

    List<CategoryDto.Response> categoriesToCategoryResponseDtos(List<Category> categories);
}
