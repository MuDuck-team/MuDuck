package MuDuck.MuDuck.comment.repository;

import MuDuck.MuDuck.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
