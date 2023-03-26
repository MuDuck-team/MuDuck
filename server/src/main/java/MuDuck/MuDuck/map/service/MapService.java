package MuDuck.MuDuck.map.service;

import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.map.entity.AvgEntity;
import MuDuck.MuDuck.map.groupcode.GroupCode;
import MuDuck.MuDuck.map.repository.MapRepository;
import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import MuDuck.MuDuck.recommendplace.service.RecommendPlaceService;
import MuDuck.MuDuck.theater.service.TheaterService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapService {
    private final MapRepository mapRepository;
    private final RecommendPlaceService recommendPlaceService;
    private final TheaterService theaterService;
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
    @Transactional
    public RecommendPlace postOrGetMap(MuDuck.MuDuck.map.entity.Map map, RecommendPlace place){

        // 해당 극장이 있는지 검증
        theaterService.findVerifiedTheater(map.getTheater().getTheaterId());

        // 해당 장소가 있는지 확인
        // 해당 좌표가 있는지도 검증
        if(!existMap(map.getPlaceId())){
            // 둘 다 없을 경우 저장
            MuDuck.MuDuck.map.entity.Map postMap = postMap(map);
            // 저장한 값을 set
            place.setMap(postMap);

        }else if(existMap(map.getPlaceId())){
            // 있을 경우 해당 장소를 가져와 set
            MuDuck.MuDuck.map.entity.Map mapToPlaceId = findMapToPlaceId(map.getPlaceId());
            place.setMap(mapToPlaceId);
        }
        // 그 다음에 추천장소 별점과 한줄평 service 로 보낸다.
        return recommendPlaceService.postRecommendPlace(place);
    }

    public MuDuck.MuDuck.map.entity.Map postMap(MuDuck.MuDuck.map.entity.Map map){

        // 해당 placeId 가 있는지 검증
        verifiedExistsMapToPlaceId(map.getPlaceId());

        return mapRepository.save(map);
    }

    public MuDuck.MuDuck.map.entity.Map findMapToPlaceId(long placeId){
        return findVerifiedMapToPlaceId(placeId);
    }

    public boolean existMap(long placeId){

        Optional<MuDuck.MuDuck.map.entity.Map> byPlaceId = mapRepository.findByPlaceId(placeId);

        return byPlaceId.isPresent();
    }

    public void verifiedExistsMapToPlaceId(long id){

        Optional<MuDuck.MuDuck.map.entity.Map> optionalMap = mapRepository.findByPlaceId(id);

        if(optionalMap.isPresent()){
           throw  new BusinessLogicException(ExceptionCode.MAP_EXISTS);
        }
    }

    public void verifiedExistsMapToId(long id){

        Optional<MuDuck.MuDuck.map.entity.Map> optionalMap = mapRepository.findById(id);

        if(optionalMap.isPresent()){
            throw  new BusinessLogicException(ExceptionCode.MAP_EXISTS);
        }
    }

    private MuDuck.MuDuck.map.entity.Map findVerifiedMapToPlaceId(long placeId){
        Optional<MuDuck.MuDuck.map.entity.Map> byPlaceId = mapRepository.findByPlaceId(placeId);

        MuDuck.MuDuck.map.entity.Map map = byPlaceId.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MAP_NOT_FOUND));

        return map;
    }

}
