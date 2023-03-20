package MuDuck.MuDuck.noticeboard.repository;

import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
    List<NoticeBoard> findTop2ByOrderByCreatedAtDesc();
}
