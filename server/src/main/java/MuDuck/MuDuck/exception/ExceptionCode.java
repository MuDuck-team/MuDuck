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
    BOARD_REMOVED(404, "Board Already Removed"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    CATEGORY_EXISTS(409, "Category exists"),
    NOT_SAME_USER(401, "Not the same user"),
    DUPLICATE_NICKNAME(409, "Duplicate nickname"),
    MUSICAL_NOT_FOUND(404,"Musical not found"),
    MUSICAL_EXISTS(409, "Musical exists"),
    INVALID_MUSICAL_STATE(400, "Invalid musical state"),
    ACTOR_NOT_FOUND(404,"Musical not found"),
    ACTOR_EXISTS(409, "Musical exists"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    COMMENT_EXISTS(409, "Comment exists"),
    COMMENT_REMOVED(404, "Comment Already Removed"),
    INVALID_COMMENT(400, "Can't post reply to reply"),
    INVALID_ACTOR_STATE(400, "Invalid musical state"),
    NOT_FOUND_COOKIE(404, "Cookie Not Found");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
