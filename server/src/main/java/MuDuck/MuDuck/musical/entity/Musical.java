package MuDuck.MuDuck.musical.entity;

import MuDuck.MuDuck.audit.Auditable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "musicals")
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
    @Column(length = 20, nullable = false)
    private String openDate;
    @Column(length = 20, nullable = false)
    private String closeDate;
    @Column(length = 3, nullable = false)
    private String age;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private Integer view;

    @OneToMany(mappedBy = "musical", cascade = CascadeType.PERSIST)
    @Lazy
    private List<ActorMusical> actorMusicals = new ArrayList<>();

//    @JoinColumn(name = "THEATER_ID")
//    @Lazy
//    private Theater theater;
//    @ManyToOne
//    @JoinColumn(name = "CATEGORY_ID")
//    @Lazy
//    private Category category;

    public void setMusicalId(Long musicalId) {
        this.musicalId = musicalId;
    }

    public void addActorMusical(ActorMusical actorMusical){
        this.actorMusicals.add(actorMusical);
        if(actorMusical.getMusical() != this){
            actorMusical.addMusical(this);
        }
    }

    public enum MusicalState{
        MUSICAL_ONAIR( 1, "공연 중" ),
        MUSICAL_YET( 2, "개막 예정" ),
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

    public enum Genre{
        GENRE_CREATED( 1, "창작" ),
        GENRE_LICENSED( 2, "라이센스" ),
        GENRE_ORIGINAL( 3, "오리지널" );

        @Getter
        private Integer stepNumber;
        @Getter
        private String stepDescription;

        Genre(Integer stepNumber, String stepDescription){
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

}
