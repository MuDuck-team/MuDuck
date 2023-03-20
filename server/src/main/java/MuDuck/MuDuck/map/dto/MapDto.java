package MuDuck.MuDuck.map.dto;

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
        @Pattern(regexp = "/^-?([1-8]?[1-9]|[1-9]0)\\.{1}\\d{1,15}",message = "올바른 경도를 입력해주세요")
        private double longitude;
        @Pattern(regexp = "/^-?([1-8]?[1-9]|[1-9]0)\\.{1}\\d{1,15}",message = "올바른 위도를 입력해주세요")
        private double latitude;
        @Length(min = 3, max = 3)
        private String categoryGroupCode;
        @Pattern(regexp = "/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}/", message = "올바른 전화번호를 입력해주세요")
        private String phone;
        private String address;
        private String roadAddress;
    }
}
