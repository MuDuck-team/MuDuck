package MuDuck.MuDuck.recommendplace.repository;

import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendPlaceRepository extends JpaRepository<RecommendPlace, Long> {
    @Query("select rp from RecommendPlace rp where rp.member.memberId = :memberId and rp.map.mapId = :mapId")
    Optional<RecommendPlace> findByMemberIdAndMapId(@Param("memberId") long memberId,@Param("mapId") long mapId);

}
