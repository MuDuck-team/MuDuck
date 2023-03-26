package MuDuck.MuDuck.musical.mapper;

import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseActors;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseMusicalBoards;
import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.MusicalBoards;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicalMapper {

    MusicalDto.ResponseMusical musicalToMusicalResponseDto(Musical musical);

    List<MusicalDto.ResponseMusicals> musicalsToMusicalResponseDtos(List<Musical> musicals);

    default MusicalDto.ResponseActors actorMusicalToMusicalResponseDto(Musical musical, ActorMusical responseActors){
        MusicalDto.ResponseActors response = ResponseActors.builder()
                .musicalId(musical.getMusicalId())
                .actorMusicals(responseActors)
                .build();
        return response;
    };

    default MusicalDto.ResponseMusicalBoards boardsToMusicalResponseDtos(Musical musical,
            List<MusicalBoards> responseBoards, Category category) {
        MusicalDto.ResponseMusicalBoards response = ResponseMusicalBoards.builder()
                .musicalId(musical.getMusicalId())
                .boards(responseBoards)
                .category(category)
                .build();
        return response;
    }
}
