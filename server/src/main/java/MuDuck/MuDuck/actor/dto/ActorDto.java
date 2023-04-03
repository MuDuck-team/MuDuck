package MuDuck.MuDuck.actor.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ActorDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @Positive
        @Setter
        private Long actorId;
        @NotNull
        private String actorName;
        @NotNull
        private String picture;
    }

    @Getter
    public static class Patch {

        private Long actorId;
        private String actorName;
        private String picture;
        private String actorState;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long actorId;
        private String actorName;
        private String picture;
    }
}
