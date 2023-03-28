package MuDuck.MuDuck.musical.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ActorMusicalResponseDto {

    @Builder
    @Getter
    @JsonPropertyOrder({"id", "actorName", "role"})
    public static class listing {

        @Positive
        @JsonProperty("id")
        private Long actorId;
        private String actorName;
        private String role;
    }

    @Builder
    @Getter
    @JsonPropertyOrder({"id", "actorName", "picture", "role"})
    public static class detail {

        @Positive
        @JsonProperty("id")
        private Long actorId;
        private String actorName;
        private String picture;
        private String role;
    }
}
