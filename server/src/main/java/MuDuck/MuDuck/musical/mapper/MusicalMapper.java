package MuDuck.MuDuck.musical.mapper;

import MuDuck.MuDuck.musical.dto.ActorMusicalResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.entity.Musical;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicalMapper {
    List<MusicalDto.ResponseMusicals> musicalsToMusicalResponseDtos(List<Musical> musicals);

    default List<ActorMusicalResponseDto.listing> actorMusicalsToActorMusicalResponseDtos(List<ActorMusical> actorMusicals){
        return actorMusicals
                .stream()
                .map(actorMusical -> ActorMusicalResponseDto.listing // 어떤 DTO를 참조할꺼?
                        .builder()
                        .actorId(actorMusical.getActor().getActorId())
                        .actorName(actorMusical.getActor().getActorName())
                        .role(actorMusical.getRole()) // 매핑값 넣기 // 빠지면 DTO에서 선언 된 값이 NULL로 나옴
                        .build())
                .collect(Collectors.toList());
    }
}
