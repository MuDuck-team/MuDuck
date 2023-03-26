package MuDuck.MuDuck.musical.dto;

import java.util.List;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

public class ActorMusicalDto {

    @Getter
    public static class Post {

        @Positive
        private Long musicalId;
        @Positive
        private String role;
    }
    @Builder
    @Getter
    public static class Response{
        private Long actorId = getActorId();
        private String actorName = getActorName();
        private String picture = getPicture();
        private String role;
    }

    @Getter
    public static class MappingActorResponseDto {

        private MusicalDto.ResponseActors musical;
        private List<MuDuck.MuDuck.musical.entity.Response> actors;

        public MappingActorResponseDto(MusicalDto.ResponseActors musical, List<MuDuck.MuDuck.musical.entity.Response> actors) {
            this.musical = musical;
            this.actors = actors;
        }
    }

}
