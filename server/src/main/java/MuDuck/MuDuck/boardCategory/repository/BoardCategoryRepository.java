package MuDuck.MuDuck.boardCategory.repository;

import MuDuck.MuDuck.boardCategory.entity.BoardCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
    @Query(value = "SELECT BOARD_ID FROM BOARD_CATEGORY as bc WHERE bc.CATEGORY_ID = :categoryId", nativeQuery = true)
    List<Long> findAllIdByCategoryId(Long categoryId);
}
