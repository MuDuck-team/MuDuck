package MuDuck.MuDuck.actorMusical.repository;

import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.entity.Musical;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorMusicalRepository extends JpaRepository<ActorMusical, Long> {
//    @Query(value = "select am.actor_id, a.actor_name, a.picture, am.role from actormusicals am Join actors a on am.actor_id = a.actor_id where am.actor_musical_id = :actorMusicalId",nativeQuery = true)
//    ActorMusical findByActorMusicalId(Long actorMusicalId);
//    @Query(value = "SELECT * FROM ACTORMUSICALS WHERE musical_id = :musicalId",nativeQuery = true)
//    ActorMusical findByMusicalId(@Param("musicalId") Long musicalId);
    List<ActorMusical> findByMusical(Musical musical);
    List<ActorMusical> findByMusicalAndRole(Musical musical, String role);

}
