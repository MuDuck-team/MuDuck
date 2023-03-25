package MuDuck.MuDuck.musical.mapper;

import MuDuck.MuDuck.musical.dto.Category;
import MuDuck.MuDuck.musical.dto.MusicalBoards;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseMusicalBoards;
import MuDuck.MuDuck.musical.entity.Musical;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicalMapper {
    MusicalDto.ResponseMusical musicalToMusicalResponseDto(Musical musical);
    List<MusicalDto.ResponseMusicals> musicalsToMusicalResponseDtos(List<Musical> musicals);
//    ActorMusicalResponseDto actorMusicalToMusicalResponseDto(Musical musical);

    default MusicalDto.ResponseMusicalBoards boardsToMusicalResponseDtos(Musical musical, List<MusicalBoards> responseBoards, Category category){
        MusicalDto.ResponseMusicalBoards response = ResponseMusicalBoards.builder()
                .musicalId(musical.getMusicalId())
                .boards(responseBoards)
                .category(category)
                .build();
        return response;
    }
}
