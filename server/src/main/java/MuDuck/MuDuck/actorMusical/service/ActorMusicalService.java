package MuDuck.MuDuck.actorMusical.service;

import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.actorMusical.repository.ActorMusicalRepository;
import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActorMusicalService {
    private ActorMusicalRepository actorMusicalRepository;

    @Transactional(readOnly = true)
    public ActorMusical findActorMusical(long actorMusicalId) {
        return findVerifiedActorMusical(actorMusicalId);
    }

    //musical-id로 actor정보 찾기
    public ActorMusical getActorMusical(Long actorMusicalId){

        ActorMusical findActorMusical = findVerifiedActorMusical(actorMusicalId);

        return findActorMusical;
    }

    public ActorMusical findVerifiedActorMusical(Long actorMusicalId){

        Optional<ActorMusical> optionalActorMusical = actorMusicalRepository.findById(actorMusicalId);

        ActorMusical findActorMusical = optionalActorMusical.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.ACTOR_MUSICAL_NOT_FOUND));

        return findActorMusical;
    }

}
