package MuDuck.MuDuck.musical.repository;

import MuDuck.MuDuck.actorMusical.entity.ActorsEntity;
import MuDuck.MuDuck.musical.dto.ActorMusicalDto.MappingActorResponseDto;
import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.MusicalBoards;
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

    @Query(value = "SELECT b.board_id AS boardId, b.title, b.created_at AS createdAt , b.views, b.likes, m.nick_name AS nickname, m.picture FROM board b JOIN member m ON b.member_id = m.member_id WHERE board_id IN (SELECT board_id  FROM board_category WHERE category_id IN (SELECT category_id FROM category WHERE musical_id = :musicalId))", nativeQuery = true)
    List<MusicalBoards> findBoardByMusicalId(@Param("musicalId") Long musicalId);

    @Query(value = "SELECT category_name AS categoryName FROM category WHERE musical_id = :musicalId", nativeQuery = true)
    Category findCategoryByMusicalId(@Param("musicalId") Long musicalId);
}
