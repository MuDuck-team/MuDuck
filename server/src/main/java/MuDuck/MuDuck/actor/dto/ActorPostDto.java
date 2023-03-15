package MuDuck.MuDuck.actor.dto;

import javax.validation.constraints.NotBlank;

public class ActorPostDto {
    @NotBlank
    private String actorName;
    @NotBlank
    private String picture;


}
