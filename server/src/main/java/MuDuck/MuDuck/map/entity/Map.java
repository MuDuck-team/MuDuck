package MuDuck.MuDuck.map.entity;

import MuDuck.MuDuck.audit.Auditable;
import MuDuck.MuDuck.map.groupcode.GroupCode;
import MuDuck.MuDuck.theater.entitiy.Theater;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Map extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mapId;

    // 극장과의 연관관계 매핑 필드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    @Column(nullable = false)
    private Long placeId;

    @Column(nullable = false, length = 30)
    private String placeName;

    // 경도
    @Column(nullable = false)
    private Long longitude;

    // 위도
    @Column(nullable = false)
    private Long latitude;

    @Enumerated(EnumType.STRING)
    private GroupCode categoryGroupCode;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false, length = 50)
    private String addressName;

    @Column(nullable = false, length = 50)
    private String roadAddressName;

    @Builder
    public Map(Long mapId, Theater theater, Long placeId, String placeName, Long longitude,
            Long latitude, GroupCode categoryGroupCode, String phone, String addressName,
            String roadAddressName) {
        this.mapId = mapId;
        this.theater = theater;
        this.placeId = placeId;
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.categoryGroupCode = categoryGroupCode;
        this.phone = phone;
        this.addressName = addressName;
        this.roadAddressName = roadAddressName;
    }
}
