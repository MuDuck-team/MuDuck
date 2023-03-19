package MuDuck.MuDuck.musical.entity;

import MuDuck.MuDuck.audit.Auditable;
import MuDuck.MuDuck.category.entity.Category;
import MuDuck.MuDuck.theater.entitiy.Theater;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "musicals")
public class Musical extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long musicalId;

    @Column(length = 100, nullable = false)
    private String musicalKorName;

    @Column(length = 100, nullable = false)
    private String musicalEngName;

    @Column(nullable = false)
    private String poster;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Genre genre = Genre.GENRE_LICENSED;

    @Column(length = 100, nullable = false)
    private String musicalInfo;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MusicalState musicalState = MusicalState.MUSICAL_ONAIR;

    @Column(length = 20, nullable = false)
    private String openDate;

    @Column(length = 20, nullable = false)
    private String closeDate;

    @Column(length = 3, nullable = false)
    private String age;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @OneToMany(mappedBy = "musical", cascade = CascadeType.PERSIST)
    @Lazy
    private List<ActorMusical> actorMusicals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    @OneToMany(mappedBy = "musical", fetch = FetchType.LAZY)
    private List<Category> category;

    public void setMusicalId(Long musicalId) {
        this.musicalId = musicalId;
    }

    public void addActorMusical(ActorMusical actorMusical) {
        this.actorMusicals.add(actorMusical);
        if (actorMusical.getMusical() != this) {
            actorMusical.addMusical(this);
        }
    }

    public enum MusicalState {
        MUSICAL_ONAIR(1, "공연 중"),
        MUSICAL_YET(2, "개막 예정"),
        MUSICAL_FINISH(3, "공연 종료");

        @Getter
        private int stepNumber;
        @Getter
        private String stepDescription;

        MusicalState(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

    public enum Genre {
        GENRE_CREATED(1, "창작"),
        GENRE_LICENSED(2, "라이센스"),
        GENRE_ORIGINAL(3, "오리지널");

        @Getter
        private int stepNumber;
        @Getter
        private String stepDescription;

        Genre(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

}