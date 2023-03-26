package MuDuck.MuDuck.recommendplace.repository;

import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendPlaceRepository extends JpaRepository<RecommendPlace, Long> {

    Optional<RecommendPlace> findByMember_MemberIdAndMap_MapId(long memberId, long mapId);

}
