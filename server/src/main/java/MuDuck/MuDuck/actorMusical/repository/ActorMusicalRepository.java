package MuDuck.MuDuck.actorMusical.repository;

import MuDuck.MuDuck.musical.entity.Response;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActorMusicalRepository extends JpaRepository<ActorMusical, Long> {
    @Query(value = "select am.actor_id as actorId , a.actor_name as actorName, a.picture, am.role from actormusicals am Join actors a on am.actor_id = a.actor_id where am.musical_id = :musicalId"
            , nativeQuery = true)
    List<Response> findActorsByMusicalId(@Param("musicalId") long musicalId);
}
