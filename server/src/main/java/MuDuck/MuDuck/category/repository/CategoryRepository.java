package MuDuck.MuDuck.category.repository;

import MuDuck.MuDuck.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
