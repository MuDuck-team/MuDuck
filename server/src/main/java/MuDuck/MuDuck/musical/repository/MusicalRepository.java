package MuDuck.MuDuck.musical.repository;

import MuDuck.MuDuck.musical.entity.ActorMusical;
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
    //@Query(value = "SELECT am.ACTOR_ID as id, am.ROLE, a.ACTOR_NAME, a.PICTURE" + "FROM ACTORMUSICALS am" + "JOIN ACTORS a on am.actor_id = " + " WHERE MUSICAL_ID = :musicalId", nativeQuery = true)
    @Query(value = "SELECT a.ACTOR_ID, b.ACTOR_NAME, b.PICTURE, a.ROLE FROM ACTORMUSICALS a JOIN ACTORS b on a.ACTOR_ID = b.ACTOR_ID WHERE a.MUSICAL_ID = :musicalId", nativeQuery = true)
    Musical findActorByMusicalId(@Param("musicalId") Long musicalId);

    @Query(value = "SELECT a.ACTOR_ID, b.ACTOR_NAME, b.PICTURE, a.ROLE FROM ACTORMUSICALS a JOIN ACTORS b on a.ACTOR_ID = b.ACTOR_ID WHERE a.MUSICAL_ID = :musicalId", nativeQuery = true)
    Musical findBoardByMusicalId(@Param("musicalId") Long musicalId);
}
