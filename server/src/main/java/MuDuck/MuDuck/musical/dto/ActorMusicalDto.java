package MuDuck.MuDuck.musical.dto;

import MuDuck.MuDuck.actor.dto.ActorDto;
import MuDuck.MuDuck.actor.dto.ActorDto.Response;
import MuDuck.MuDuck.actorMusical.entity.ActorsEntity;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseMusical;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.entity.Musical;
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

    @AllArgsConstructor
    @Getter
    public static class ResponseActorMusical{
        private String role;
    }

    public static class ResponseActorsToMusical{
        private ActorsEntity actorsEntity;
        private ActorMusical actorMusical;
    }

    @Getter
    public static class MappingActorResponseDto<T> {

        private ResponseActorMusical actorMusical;
        private Response actor;

        public MappingActorResponseDto(ResponseActorMusical actorMusical, Response actor) {
            this.actorMusical = actorMusical;
            this.actor = actor;
        }
    }

}
