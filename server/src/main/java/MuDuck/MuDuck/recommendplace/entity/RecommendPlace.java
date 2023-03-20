package MuDuck.MuDuck.recommendplace.entity;

import MuDuck.MuDuck.audit.Auditable;
import MuDuck.MuDuck.map.entity.Map;
import MuDuck.MuDuck.member.entity.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RecommendPlace extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recommendPlaceId;

    // 회원과의 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 지도와의 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAP_ID")
    private Map map;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false, length = 50)
    private String oneLine;

    @Builder
    public RecommendPlace(long recommendPlaceId, Member member, Map map, double score,
            String oneLine) {
        this.recommendPlaceId = recommendPlaceId;
        this.member = member;
        this.map = map;
        this.score = score;
        this.oneLine = oneLine;
    }
}
