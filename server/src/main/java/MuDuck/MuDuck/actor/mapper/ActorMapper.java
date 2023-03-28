package MuDuck.MuDuck.actor.mapper;

import MuDuck.MuDuck.actor.dto.ActorDto;
import MuDuck.MuDuck.actor.entity.Actor;

public interface ActorMapper {
    ActorDto.Response actorToActorMusicalResponse(Actor actor);
}
