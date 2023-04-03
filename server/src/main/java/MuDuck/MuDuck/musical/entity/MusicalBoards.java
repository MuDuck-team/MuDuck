package MuDuck.MuDuck.musical.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// BoardDto기준
@JsonPropertyOrder({"boardId","title", "nickname","picture", "createdAt","views","likes","comment_count"})
public interface MusicalBoards {
    Long getBoardId();
    String getTitle();
    String getNickname();
    String getPicture();
    String getCreatedAt();
    Integer getViews();
    Integer getLikes();
    //Integer getCommentCount(); // 댓글갯수

}
