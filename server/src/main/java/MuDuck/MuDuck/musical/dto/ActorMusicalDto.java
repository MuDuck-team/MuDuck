package MuDuck.MuDuck.musical.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

public class ActorMusicalDto {

    @Getter
    public static class Post {

        @Positive
        private Long musicalId;
        @Positive
        private String role;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private Long musicalId;
        private String actorName;
        private String picture;
    }
}
