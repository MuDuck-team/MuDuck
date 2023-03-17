package MuDuck.MuDuck.theater.repository;

import MuDuck.MuDuck.theater.entitiy.Theater;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

}
