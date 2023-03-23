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
    BOARD_EXISTS(409, "Board exists"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    CATEGORY_EXISTS(409, "Category exists"),
    NOT_SAME_USER(401, "Not the same user"),
    MUSICAL_NOT_FOUND(404,"Musical not found"),
    MUSICAL_EXISTS(409, "Musical exists"),
    INVALID_MUSICAL_STATE(400, "Invalid musical state"),
    ACTOR_NOT_FOUND(404,"Musical not found"),
    ACTOR_EXISTS(409, "Musical exists"),
    INVALID_ACTOR_STATE(400, "Invalid musical state");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
