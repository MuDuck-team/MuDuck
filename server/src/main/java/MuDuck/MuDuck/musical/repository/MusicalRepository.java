package MuDuck.MuDuck.musical.repository;

import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MusicalRepository extends JpaRepository<Musical, Long> {

    Optional<Musical> findByMusicalId(Long musicalId);

    Optional<Musical> findByMusicalKorName(String musicalKorName);

    @Query(value = "SELECT * FROM MUSICALS as m WHERE m.GENRE = :genre", nativeQuery = true)
    Page<Musical> findByGenre(String genre, Pageable pageable);

    @Query(value = "SELECT * FROM MUSICALS as m WHERE m.MUSICAL_STATE = :state", nativeQuery = true)
    Page<Musical> findByMusicalState(String state, Pageable pageable);

    @Query(value = "SELECT category_name AS categoryName FROM category WHERE musical_id = :musicalId", nativeQuery = true)
    Category findCategoryByMusicalId(@Param("musicalId") Long musicalId);
}
