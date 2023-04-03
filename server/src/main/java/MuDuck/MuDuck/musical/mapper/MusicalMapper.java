package MuDuck.MuDuck.musical.mapper;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface MusicalMapper {

    MusicalDto.ResponseMusical musicalToMusicalResponseDto(Musical musical);

    List<MusicalDto.ResponseMusicals> musicalsToMusicalResponseDtos(List<Musical> musicals);

    default MusicalDto.ResponseMusicalBoards boardsToMusicalResponseDtos(long musicalId, List<BoardDto.MusicalBoards> boards, Category category){
        MusicalDto.ResponseMusicalBoards response = MusicalDto.ResponseMusicalBoards.builder()
                .musicalId(musicalId)
                .boards(boards)
                .category(category)
                .build();
        return response;
    }

    MusicalDto.ResponseActors musicalToActorMusicalResponseDto(Musical musical);
}
