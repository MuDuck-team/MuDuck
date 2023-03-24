package MuDuck.MuDuck.musical.mapper;

import MuDuck.MuDuck.musical.dto.ActorMusicalResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.theater.dto.TheaterDto;
import MuDuck.MuDuck.theater.entitiy.Theater;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicalMapper {
    MusicalDto.ResponseMusical musicalToMusicalResponseDto(Musical musical);
    List<MusicalDto.ResponseMusicals> musicalsToMusicalResponseDtos(List<Musical> musicals);
    ActorMusicalResponseDto actorMusicalToMusicalResponseDto(ActorMusical actorMusical);
}
