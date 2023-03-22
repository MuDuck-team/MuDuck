package MuDuck.MuDuck.boardLike.repository;

import MuDuck.MuDuck.boardLike.entity.BoardLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByMemberMemberId(Long memberId);
}
