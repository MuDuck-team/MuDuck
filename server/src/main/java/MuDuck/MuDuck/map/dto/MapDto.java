package MuDuck.MuDuck.map.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

public class MapDto {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class Post{

        @Positive
        private long theaterId;
        @Positive
        private long placeId;
        @Length(min = 1, max = 30)
        private String placeName;
        @DecimalMin(value = "124.0", message = "경도를 최소 124.0 이상으로 입력해주세요")
        @DecimalMax(value = "133.0", message = "경도를 최대 133.0 이하로 입력해주세요")
        private double longitude;
        @DecimalMin(value = "33.0", message = "위도를 최소 33.0 이상으로 입력해주세요")
        @DecimalMax(value = "44.0", message = "위도를 최대 44.0 이하로 입력해주세요")
        private double latitude;
        @Length(min = 3, max = 3)
        private String categoryGroupCode;
        @Pattern(regexp = "^\\d{2,3}-?\\d{3,4}-?\\d{4}$", message = "올바른 전화번호를 입력해주세요")
        private String phone;
        private String address;
        private String roadAddress;
    }
}
