package MuDuck.MuDuck.noticeboard.entity;

import MuDuck.MuDuck.audit.Auditable;
import MuDuck.MuDuck.noticeboard.status.NoticeStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoard extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeBoardId;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private int views = 0;

    @Enumerated(EnumType.STRING)
    private NoticeStatus noticeBoardStatus = NoticeStatus.NOTICE_POST;

    @Builder
    public NoticeBoard(long noticeBoardId, String title, String body) {
        this.noticeBoardId = noticeBoardId;
        this.title = title;
        this.body = body;
    }
}

