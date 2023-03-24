package MuDuck.MuDuck.musical.dto;

import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"id", "actorName", "picture", "role"})
public class ActorMusicalResponseDto {
    @Positive
    @JsonProperty("id")
    private Long actorId;
    private String actorName;
    private String picture;
    private String role;
    public ActorMusicalResponseDto(ActorMusical actorMusical) {
        this.actorId = actorMusical.getActor().getActorId();
        this.actorName = actorMusical.getActor().getActorName();
        this.picture = actorMusical.getActor().getPicture();
        this.role = actorMusical.getRole();
    }

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
    @JsonPropertyOrder({"id", "actorName", "picture", "role"})
    public static class detail{
        @Positive
        @JsonProperty("id")
        private Long actorId;
        private String actorName;
        private String picture;
        private String role;
    }
}
