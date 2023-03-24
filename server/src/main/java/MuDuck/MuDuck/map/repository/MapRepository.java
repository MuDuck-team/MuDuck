package MuDuck.MuDuck.map.repository;

import MuDuck.MuDuck.map.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {

//    @Query(value = "SELECT * FROM "
//            + "(SELECT *, RANK() OVER (PARTITION BY CATEGORY_GROUP_CODE ORDER BY AVG_SCORE DESC) AS RN FROM "
//            + "(SELECT AVG(SCORE) AS AVG_SCORE, rp.MAP_ID, CATEGORY_GROUP_CODE FROM MAP m JOIN RECOMMEND_PLACE rp ON m.MAP_ID=rp.MAP_ID where theater_id = :theaterId GROUP BY CATEGORY_GROUP_CODE, rp.MAP_ID)) AS RANKING "
//            + "WHERE RANKING.RN <= :limit  and category_group_code = :categoryGroupCode order by category_group_code, ranking.rn", nativeQuery = true)
//    List<Map> findTop5ByCategoryGroupCodeAndTheaterId(long theaterId,String categoryGroup, int limit);
}
