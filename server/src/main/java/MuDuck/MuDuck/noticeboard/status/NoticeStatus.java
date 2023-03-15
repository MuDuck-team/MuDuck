package MuDuck.MuDuck.noticeboard.status;

import lombok.Getter;

public enum NoticeStatus {
    NOTICE_POST("게시", "POST"),
    NOTICE_DELETE("삭제", "DELETE");
    @Getter
    private final String title;

    @Getter
    private final String status;

    NoticeStatus(String title, String status) {
        this.title = title;
        this.status = status;
    }
}
