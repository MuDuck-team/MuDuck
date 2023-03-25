package MuDuck.MuDuck.map.service;

import MuDuck.MuDuck.map.entity.AvgEntity;
import MuDuck.MuDuck.map.groupcode.GroupCode;
import MuDuck.MuDuck.map.repository.MapRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class MapService {
    private final MapRepository mapRepository;
    private final int LIMIT = 5;
    public Map<GroupCode, List<AvgEntity>> getMapsWithAvgScoreAndTotalReviews(long theaterId){

        List<AvgEntity> avgEntities = mapRepository.findTop5AllCategoryGroupCode(theaterId, LIMIT);

        //log.info(avgEntities.get(0).getAvgScore());

        Map<GroupCode, List<AvgEntity>> stringListMap = groupByGroupCode(avgEntities);

        return stringListMap;
    }

    public Map<GroupCode, List<AvgEntity>> groupByGroupCode(List<AvgEntity> lists){

        Map<GroupCode, List<AvgEntity>> response = lists.stream().collect(Collectors.groupingBy(AvgEntity::getCategoryGroupCode));

        return response;
    }
}
