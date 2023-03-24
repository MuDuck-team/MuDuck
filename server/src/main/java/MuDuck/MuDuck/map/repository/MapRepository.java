package MuDuck.MuDuck.map.repository;

import MuDuck.MuDuck.map.entity.Map;
import MuDuck.MuDuck.map.groupcode.GroupCode;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {
    List<Map> findTop5ByCategoryGroupCode(GroupCode categoryGroupCode);
}
