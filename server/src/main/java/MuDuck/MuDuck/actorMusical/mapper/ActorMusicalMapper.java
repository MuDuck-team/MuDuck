package MuDuck.MuDuck.actorMusical.mapper;

import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.musical.dto.ActorMusicalResponseDto;
import MuDuck.MuDuck.musical.dto.ActorMusicalResponseDto.detail;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMusicalMapper {
    default ActorMusicalResponseDto.detail musicalToActorResponseDtos(Actor actor,
            ActorMusical actorMusical){
        ActorMusicalResponseDto.detail response = detail.builder()
                .actorId(actor.getActorId())
                .actorName(actor.getActorName())
                .picture(actor.getPicture())
                .role(actorMusical.getRole())
                .build();
        return response;
    }

}
