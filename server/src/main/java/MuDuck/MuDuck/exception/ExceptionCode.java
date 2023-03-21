package MuDuck.MuDuck.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    THEATER_NOT_FOUND(404, "Theater not found"),
    THEATER_EXISTS(409, "Theater exists"),
    BOARD_NOT_FOUND(404, "Board not found"),
    BOARD_EXISTS(409, "Board exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
