package MuDuck.MuDuck.board.repository;

import MuDuck.MuDuck.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b WHERE NOT b.boardStatus IN ('BOARD_DELETE')")
    Page<Board> findWithoutDeleteBoard(PageRequest pageRequest);
}
