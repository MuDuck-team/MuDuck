package MuDuck.MuDuck.noticeboard.repository;

import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
    @Query(value = "SELECT * FROM (SELECT * FROM NOTICE_BOARD ORDER BY CREATED_AT DESC) WHERE ROWNUM <= :topNum", nativeQuery = true)
    List<NoticeBoard> findTopNoticeBoardBy(int topNum);
}
