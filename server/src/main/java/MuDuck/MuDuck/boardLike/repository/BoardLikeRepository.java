package MuDuck.MuDuck.boardLike.repository;

import MuDuck.MuDuck.boardLike.entity.BoardLike;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    @Query(value = "SELECT * FROM BOARD_LIKE AS BL WHERE BL.BOARD_ID = :boardId AND BL.MEMBER_ID = :memberId", nativeQuery = true)
    Optional<BoardLike> isMemberClickLike(long boardId, long memberId);

    @Query(value = "SELECT BOARD_LIKES_ID FROM BOARD_LIKE WHERE BOARD_ID = :boardId AND MEMBER_ID = :memberId", nativeQuery = true)
    long findBoardLikeId(long boardId, long memberId);
}
