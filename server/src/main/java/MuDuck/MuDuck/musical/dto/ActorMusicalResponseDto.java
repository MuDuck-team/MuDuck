package MuDuck.MuDuck.musical.dto;

import MuDuck.MuDuck.actor.entity.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

public class ActorMusicalResponseDto {
    @Builder
    @Getter
    @JsonPropertyOrder({"id", "actorName", "role"})
    public static class listing{
        @Positive
        @JsonProperty("id")
        private Long actorId;
        private String actorName;
        private String role;
    }
    @Builder
    @Getter
    @JsonPropertyOrder({"id", "actorName", "picture", "role"}) // responseBody순서
    public static class detail{
        @Positive
        @JsonProperty("id") // responseBody 변수명 지정
        private Long actorId;
        private String actorName;
        private String picture;
        private Actor.ActorState actorState;
        private String role;
    }
}
