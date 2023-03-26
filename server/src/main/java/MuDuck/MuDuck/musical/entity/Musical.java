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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "musicals")
@Builder
public class Musical extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicalId;
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
    @Column(length = 11, nullable = false)
    private String openDate;
    @Column(length = 11, nullable = false)
    private String closeDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Age age = Age.AGE_15;
    @Column(length = 3, nullable = false)

    private Integer runningTime;
    @Column(length = 3, nullable = false)

    private Integer intermission;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private Integer views;

    @OneToMany(mappedBy = "musical", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ActorMusical> actorMusicals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    @OneToMany(mappedBy = "musical", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    public Musical(Long musicalId, String musicalKorName, String musicalEngName, String poster,
            Genre genre, String musicalInfo, MusicalState musicalState, String openDate,
            String closeDate, Age age, Integer runningTime, Integer intermission, Integer views, Theater theater
    ) {
        this.musicalId = musicalId;
        this.musicalKorName = musicalKorName;
        this.musicalEngName = musicalEngName;
        this.poster = poster;
        this.genre = genre;
        this.musicalInfo = musicalInfo;
        this.musicalState = musicalState;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.age = age;
        this.runningTime = runningTime;
        this.intermission = intermission;
        this.views = views;
        this.theater = theater;
    }

    public Musical(String musicalKorName, String musicalEngName, String poster,
            Genre genre, String musicalInfo, MusicalState musicalState, String openDate,
            String closeDate, Age age, int runningTime, int intermission) {
        this.musicalKorName = musicalKorName;
        this.musicalEngName = musicalEngName;
        this.poster = poster;
        this.genre = genre;
        this.musicalInfo = musicalInfo;
        this.musicalState = musicalState;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.age = age;
        this.runningTime = runningTime;
        this.intermission = intermission;
    }

    public void setMusicalId(Long musicalId) {
        this.musicalId = musicalId;
    }

    public void addActorMusical(ActorMusical actorMusical){
        this.actorMusicals.add(actorMusical);
        if(actorMusical.getMusical() != this){
            actorMusical.addMusical(this);
        }
    }

    public enum Genre{
        GENRE_CREATED( "창작" ),
        GENRE_LICENSED( "라이센스" ),
        GENRE_ORIGINAL( "오리지널" );

        @Getter
        private String description;

        Genre(String description){
            this.description = description;
        }
    }

    public enum MusicalState{
        MUSICAL_YET( 1, "개막 예정" ),
        MUSICAL_ONAIR( 2, "공연 중" ),
        MUSICAL_FINISH( 3, "공연 종료" );

        @Getter
        private Integer stepNumber;
        @Getter
        private String stepDescription;

        MusicalState(Integer stepNumber, String stepDescription){
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

    public enum Age{
        AGE_0( 1, "전체관람가" ),
        AGE_12( 2, "12세 이상 관람가" ),
        AGE_15( 3, "15세 이상 관람가" ),
        AGE_19( 4, "청소년 관람불가" );

        @Getter
        private Integer stepNumber;
        @Getter
        private String stepDescription;

        Age(Integer stepNumber, String stepDescription){
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

}
