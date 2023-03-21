package MuDuck.MuDuck.response;

import MuDuck.MuDuck.map.dto.MapDto;
import MuDuck.MuDuck.map.dto.MapDto.Post;
import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto;
import javax.validation.Valid;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class RecommendPlaceMultipleResponse {
    @Valid
    private final MapDto.Post map;
    @Valid
    private final RecommendPlaceDto.Post recommendPlace;

    public RecommendPlaceMultipleResponse(Post map, RecommendPlaceDto.Post recommendPlace) {
        this.map = map;
        this.recommendPlace = recommendPlace;
    }
}
