package MuDuck.MuDuck.category.service;

import MuDuck.MuDuck.category.entity.Category;
import MuDuck.MuDuck.category.repository.CategoryRepository;
import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findCategories(){
        return categoryRepository.findAll();
    }

    public Category findCategory(long categoryId){
        return findVerifiedCategory(categoryId);
    }

    private Category findVerifiedCategory(long categoryId){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Category findCategory = optionalCategory.orElseThrow(() -> new BusinessLogicException(
                ExceptionCode.CATEGORY_NOT_FOUND));
        return findCategory;
    }
}
