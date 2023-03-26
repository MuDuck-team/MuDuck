package MuDuck.MuDuck.actorMusical.service;

import MuDuck.MuDuck.actorMusical.repository.ActorMusicalRepository;
import MuDuck.MuDuck.musical.entity.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorMusicalService {
    private final ActorMusicalRepository actorMusicalRepository;

    public List<Response> getMusicalActors(long musicalId){
        return actorMusicalRepository.findActorsByMusicalId(musicalId);
    }

}
