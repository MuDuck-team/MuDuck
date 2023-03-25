package MuDuck.MuDuck.musical.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// BoardDto기준
@JsonPropertyOrder({"title", "nickname", "createdAt","views","likes"})
public interface MusicalBoards {
    String getTitle();
    String getNickname();
    String getCreatedAt();
    Integer getViews();
    Integer getLikes();
}
