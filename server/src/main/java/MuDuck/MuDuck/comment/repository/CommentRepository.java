package MuDuck.MuDuck.comment.repository;

import MuDuck.MuDuck.comment.entity.Comment;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM COMMENT AS C WHERE MEMBER_ID = 1 AND COMMENT_STATUS != 'COMMENT_DELETE' AND C.BOARD_ID NOT IN (SELECT BOARD_ID FROM BOARD WHERE BOARD_STATUS = 'BOARD_DELETE')", nativeQuery = true)
    Page<Comment> findByMemberId(long memberId, PageRequest pageRequest);
}
