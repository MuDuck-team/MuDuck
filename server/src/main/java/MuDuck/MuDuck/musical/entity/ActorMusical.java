package MuDuck.MuDuck.musical.entity;

import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.audit.Auditable;
import MuDuck.MuDuck.musical.entity.Musical;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Lazy;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "actormusicals")
public class ActorMusical extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicalActorId;

    @Column(length = 10, nullable = false)
    private String role;

    @ManyToOne
    @JoinColumn(name = "MUSICAL_ID")
    @Lazy
    private Musical musical;

    @ManyToOne
    @JoinColumn(name = "ACTOR_ID")
    @Lazy
    private Actor actor;

    public void addMusical(Musical musical) {
        this.musical = musical;
        if (!this.musical.getActorMusicals().contains(this)) {
            this.musical.getActorMusicals().add(this);
        }
    }

    public void addActor(Actor actor) {
        this.actor = actor;
        if (!this.actor.getActorMusicals().contains(this)) {
            this.actor.addActorMusical(this);
        }
    }
}
