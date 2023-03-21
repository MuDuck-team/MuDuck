package MuDuck.MuDuck.board.repository;

import MuDuck.MuDuck.board.entity.Board;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b WHERE NOT b.boardStatus IN ('BOARD_DELETE')")
    Page<Board> findWithoutDeleteBoard(PageRequest pageRequest);

    @Query(value = "SELECT * FROM BOARD as b WHERE b.BOARD_ID IN (:ids)", nativeQuery = true)
    Page<Board> findByBoardId(List<Long> ids, PageRequest pageRequest);
}
