package MuDuck.MuDuck.category.service;

import MuDuck.MuDuck.category.entity.Category;
import MuDuck.MuDuck.category.repository.CategoryRepository;
import java.util.List;
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
}
