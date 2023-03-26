package MuDuck.MuDuck.actorMusical.mapper;

import MuDuck.MuDuck.actorMusical.entity.ActorsEntity;
import MuDuck.MuDuck.musical.dto.ActorMusicalDto;
import MuDuck.MuDuck.musical.dto.ActorMusicalDto.MappingActorResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseActors;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseMusicalBoards;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.MusicalBoards;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMusicalMapper {
    ActorMusicalDto.ResponseActorMusical actorsToMusicalDto(ActorMusical actorMusical);
    default MusicalDto.ResponseActors actorsToMusicalResponseDtos(Musical musical,
            List<MappingActorResponseDto> responseActors) {
        MusicalDto.ResponseActors response = ResponseActors.builder()
                .musicalId(musical.getMusicalId())
                .actorMusicals(responseActors)
                .build();
        return response;
    }
}
