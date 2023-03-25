package MuDuck.MuDuck.map.controller;

import MuDuck.MuDuck.map.dto.AvgDto;
import MuDuck.MuDuck.map.entity.AvgEntity;
import MuDuck.MuDuck.map.groupcode.GroupCode;
import MuDuck.MuDuck.map.mapper.MapMapper;
import MuDuck.MuDuck.map.service.MapService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/maps")
public class MapController {
    private final MapService mapService;
    private final MapMapper mapMapper;

    @GetMapping("/theater/{theater-id}")
    public ResponseEntity getMapsToTheaterId(@PathVariable("theater-id") long theaterId){

        Map<GroupCode, List<AvgEntity>> mapsWithAvgScoreAndTotalReviews = mapService.getMapsWithAvgScoreAndTotalReviews(
                theaterId);

        Map<String, List<AvgDto.Response>> groupCodeListMap = mapMapper.mapAvgEntity(
                mapsWithAvgScoreAndTotalReviews);

        return new ResponseEntity(groupCodeListMap, HttpStatus.OK);
    }
}
