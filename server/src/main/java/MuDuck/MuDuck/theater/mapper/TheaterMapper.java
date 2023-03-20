package MuDuck.MuDuck.theater.mapper;

import MuDuck.MuDuck.theater.dto.TheaterDto;
import MuDuck.MuDuck.theater.entitiy.Theater;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TheaterMapper {
    @Mapping(target = "id", source = "theater.theaterId")
    TheaterDto.Response theaterToTheaterResponse(Theater theater);

    List<TheaterDto.Response> theaterListToTheaterResponseList(List<Theater> theaterList);
}
