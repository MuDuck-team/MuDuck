package MuDuck.MuDuck.recommendplace.mapper;

import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto;
import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecommendPlaceMapper {
    @Mapping(target = "member.memberId", source = "memberId")
    RecommendPlace postDtoToRecommendPlace(RecommendPlaceDto.Post post);
    @Mapping(target = "id", source = "recommendPlaceId")
    @Mapping(target = "memberId", source = "member.memberId")
    @Mapping(target = "mapId", source = "map.mapId")
    RecommendPlaceDto.Response recommendPlaceToResponse(RecommendPlace place);


    RecommendPlace patchDtoToRecommendPlace(RecommendPlaceDto.Patch patch);
}
