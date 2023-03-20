package MuDuck.MuDuck.actor.entity;

import MuDuck.MuDuck.musical.entity.ActorMusical;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import org.springframework.context.annotation.Lazy;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long actorId;
    @Column(length = 20, nullable = false)
    private String actorName;
    @Column(nullable = false)
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private ActorState actorState = ActorState.ACTOR_CONFIRM;
    @OneToMany(mappedBy = "actor", cascade = CascadeType.PERSIST)
    @Lazy
    private List<ActorMusical> actorMusicals = new ArrayList<>();

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public void addActorMusical(ActorMusical actorMusical){
        this.actorMusicals.add(actorMusical);
        if(actorMusical.getActor() != this){
            actorMusical.addActor(this);
        }
    }

    public enum ActorState{
        ACTOR_CONFIRM(1, "등록됨"),
        ACTOR_DELETE(2,"삭제됨");

        @Getter
        private Integer stepNumber;
        @Getter
        private String stepDescription;

        ActorState(Integer stepNumber, String stepDescription){
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
