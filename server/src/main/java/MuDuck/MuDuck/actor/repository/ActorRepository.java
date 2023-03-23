package MuDuck.MuDuck.actor.repository;

import MuDuck.MuDuck.actor.entity.Actor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByPicture(String picture);
    Optional<Actor> findByActorId(Long actorId);
}
