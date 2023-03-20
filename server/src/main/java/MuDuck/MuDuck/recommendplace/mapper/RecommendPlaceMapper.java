package MuDuck.MuDuck.recommendplace.mapper;

import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto;
import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecommendPlaceMapper {

    RecommendPlace postDtoToRecommendPlace(RecommendPlaceDto.Post post);

}
