package MuDuck.MuDuck.actor.entity;

import MuDuck.MuDuck.musical.entity.ActorMusical;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    @Column(length = 20, nullable = false)
    private String actorName;
    @Column(nullable = false, unique = true)
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private ActorState actorState = ActorState.ACTOR_CONFIRM;
    @OneToMany(mappedBy = "actor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ActorMusical> actorMusicals = new ArrayList<>();

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public Actor(Long actorId, String actorName, String picture) {
        this.actorId = actorId;
        this.actorName = actorName;
        this.picture = picture;
    }

    public Actor(String actorName, String picture) {
        this.actorName = actorName;
        this.picture = picture;
    }

    public void addActorMusical(ActorMusical actorMusical){
        this.actorMusicals.add(actorMusical);
        if(actorMusical.getActor() != this){
            actorMusical.addActor(this);
        }
    }

    public enum ActorState{
        ACTOR_CONFIRM("등록됨"),
        ACTOR_DELETE("삭제됨");

        @Getter
        private String status;

        ActorState(String status){
            this.status = status;
        }
    }
}