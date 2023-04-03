package MuDuck.MuDuck.actorMusical.mapper;

import MuDuck.MuDuck.musical.dto.ActorMusicalDto.Response;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMusicalMapper {

    default Response actorToMusicalResponse(ActorMusical actorMusical){
        Response response = Response.builder()
                .actorId(actorMusical.getActor().getActorId())
                .actorName(actorMusical.getActor().getActorName())
                .picture(actorMusical.getActor().getPicture())
                .role(actorMusical.getRole())
                .build();
        return response;
    }

    List<Response> actorsToMusicalResponses(List<ActorMusical> actorMusicals);
}
