package MuDuck.MuDuck.map.repository;

import MuDuck.MuDuck.map.entity.AvgEntity;
import MuDuck.MuDuck.map.entity.Map;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MapRepository extends JpaRepository<Map, Long> {

    @Query(value = "SELECT * FROM "
            + "(SELECT *, RANK() OVER (PARTITION BY categoryGroupCode ORDER BY avgScore DESC) AS RN FROM "
            + "(SELECT rp.MAP_ID as mapId, place_id as placeId, place_name as placeName, address, road_address, phone,AVG(SCORE) AS avgScore ,  COUNT(rp.MAP_ID) AS totalReviews, longitude, latitude, place_url as placeUrl ,CATEGORY_GROUP_CODE as categoryGroupCode FROM MAP m JOIN RECOMMEND_PLACE rp ON m.MAP_ID=rp.MAP_ID where theater_id = :theaterId GROUP BY categoryGroupCode, mapId)) AS RANKING "
            + "WHERE RANKING.RN <= :limit  and categoryGroupCode = :categoryGroupCode order by categoryGroupCode, ranking.rn", nativeQuery = true)
    List<AvgEntity> findTop5ByCategoryGroupCodeAndTheaterId(long theaterId,String categoryGroup, int limit);
    @Query(value = "SELECT * FROM "
            + "(SELECT *, RANK() OVER (PARTITION BY categoryGroupCode ORDER BY avgScore DESC) AS RN FROM "
            + "(SELECT rp.MAP_ID as mapId, place_id as placeId, place_name as placeName, address, road_address as roadAddress , phone, AVG(SCORE) AS avgScore ,  COUNT(rp.MAP_ID) AS totalReviews, longitude, latitude, place_url as placeUrl ,CATEGORY_GROUP_CODE as categoryGroupCode FROM MAP m JOIN RECOMMEND_PLACE rp ON m.MAP_ID=rp.MAP_ID where theater_id = :theaterId GROUP BY categoryGroupCode, mapId)) AS RANKING "
            + "WHERE RANKING.RN <= :limit order by categoryGroupCode, ranking.rn", nativeQuery = true)
    List<AvgEntity> findTop5AllCategoryGroupCode(long theaterId, int limit);
}
