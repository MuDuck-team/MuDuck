package MuDuck.MuDuck.musical.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","actorName","picture","role"})
public interface Response {
    @JsonProperty("id")
    Long getActorId();
    String getActorName();
    String getPicture();
    String getRole();
}
