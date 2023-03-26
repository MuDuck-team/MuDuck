package MuDuck.MuDuck.actor.mapper;

import MuDuck.MuDuck.actor.dto.ActorDto;
import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.theater.dto.TheaterDto;
import MuDuck.MuDuck.theater.entitiy.Theater;
import org.mapstruct.Mapping;

public interface ActorMapper {
    ActorDto.Response actorToActorMusicalResponse(Actor actor);
}
