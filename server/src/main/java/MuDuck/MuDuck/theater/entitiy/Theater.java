package MuDuck.MuDuck.theater.entitiy;

import MuDuck.MuDuck.audit.Auditable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theater extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long theaterId;
    @Column(nullable = false, length = 30)
    private String placeName;

    // 경도
    @Column(nullable = false)
    private double longitude;

    // 위도
    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 50)
    private String address;
    @Column(nullable = false, length = 50)
    private String roadAddress;
    @Builder
    public Theater(long theaterId, String placeName, double longitude, double latitude, String phone,
            String address, String roadAddress) {
        this.theaterId = theaterId;
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.address = address;
        this.roadAddress = roadAddress;
    }
}
