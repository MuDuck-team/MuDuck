package MuDuck.MuDuck.category.repository;

import MuDuck.MuDuck.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT CATEGORY_ID FROM CATEGORY WHERE CATEGORY_NAME = :categoryName", nativeQuery = true)
    Long findCategoryIdByCategoryName(String categoryName);
}
