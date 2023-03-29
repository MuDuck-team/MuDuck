package MuDuck.MuDuck.musical.repository;

import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MusicalRepository extends JpaRepository<Musical, Long> {

    @Query(value = "select am.musical_id, am.actor_id, a.actor_name, a.picture, am.role from actormusicals am join actors a on am.actor_id = a.actor_id", nativeQuery = true)
    Page<Musical> findAllActors(Pageable pageable);

    Optional<Musical> findByMusicalId(Long musicalId);

    Optional<Musical> findByMusicalKorName(String musicalKorName);

    @Query(value = "SELECT * FROM MUSICALS as m WHERE m.GENRE = :genre", nativeQuery = true)
    Page<Musical> findByGenre(String genre, Pageable pageable);

    @Query(value = "SELECT * FROM MUSICALS as m WHERE m.MUSICAL_STATE = :state", nativeQuery = true)
    Page<Musical> findByMusicalState(String state, Pageable pageable);

    @Query(value = "SELECT category_name AS categoryName FROM category WHERE musical_id = :musicalId", nativeQuery = true)
    Category findCategoryByMusicalId(@Param("musicalId") Long musicalId);

    @Query(value = "SELECT * FROM musicals ORDER BY views LIMIT 4", nativeQuery = true)
    List<Musical> findRecommendMusicals();
}
