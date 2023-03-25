package MuDuck.MuDuck.musical.repository;

import MuDuck.MuDuck.musical.dto.Category;
import MuDuck.MuDuck.musical.dto.MusicalBoards;
import MuDuck.MuDuck.musical.entity.Musical;
import java.util.List;
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

    @Query(value = "select a.title, a.created_at as createdAt , a.views, a.likes, b.nick_name as nickname from board a join member b ON a.member_id = b.member_id where board_id IN (select board_id  from board_category where category_id IN (SELECT category_id from category where musical_id = :musicalId))", nativeQuery = true)
    List<MusicalBoards> findBoardByMusicalId(@Param("musicalId") Long musicalId);

    @Query(value = "SELECT category_name as categoryName from category where musical_id = :musicalId", nativeQuery = true)
    Category findCategoryByMusicalId(@Param("musicalId") Long musicalId);
}
