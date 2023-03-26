package MuDuck.MuDuck.musical.entity;

import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.audit.Auditable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "actormusicals")
public class ActorMusical extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicalActorId;

    @Column(length = 10)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUSICAL_ID")
    private Musical musical;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTOR_ID")
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
