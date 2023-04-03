package MuDuck.MuDuck.actor.service;

import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.actor.repository.ActorRepository;
import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor createActor(Actor actor){
        verifyExistsActor(actor.getPicture());
        Actor savedActor = actorRepository.save(actor);

        return savedActor;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Actor updateActor(Actor actor){
        Actor findActor = findVerifiedActor(actor.getActorId());

        Optional.ofNullable(actor.getActorName())
                .ifPresent(actorName -> findActor.setActorName(actorName));
        Optional.ofNullable(actor.getPicture())
                .ifPresent(picture -> findActor.setPicture(picture));
        Optional.ofNullable(actor.getActorState())
                .ifPresent(actorState -> findActor.setActorState(actorState));

        return actorRepository.save(findActor);
    }

    public Actor findActor(long actorId){
        return findVerifiedActor(actorId);
    }

    public Page<Actor> findActors(int page, int size){
        return actorRepository.findAll(PageRequest.of(page, size, Sort.by("role").descending()));
    }

    @Transactional(readOnly = true)
    public Actor findVerifiedActor(long actorId){
        Optional<Actor> optionalActor = actorRepository.findById(actorId);
        Actor findActor = optionalActor.orElseThrow(()->new BusinessLogicException(ExceptionCode.ACTOR_NOT_FOUND));
        return findActor;
    }

    public void verifyExistsActor(String picture) {
        Optional<Actor> actor = actorRepository.findByPicture(picture);
        if (actor.isPresent())
            throw new BusinessLogicException(ExceptionCode.ACTOR_EXISTS);
    }

    private Actor saveActor(Actor actor){
        return actorRepository.save(actor);
    }
}