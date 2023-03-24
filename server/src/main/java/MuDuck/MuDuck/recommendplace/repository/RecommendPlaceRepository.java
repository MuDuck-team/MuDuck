package MuDuck.MuDuck.recommendplace.repository;

import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendPlaceRepository extends JpaRepository<RecommendPlace, Long> {


}
