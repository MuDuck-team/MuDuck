package MuDuck.MuDuck.board.repository;

import MuDuck.MuDuck.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
