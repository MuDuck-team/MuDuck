package MuDuck.MuDuck.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AvgDto {
    @Getter
    @AllArgsConstructor
    public static class Response {
        private long id;
        private long placeId;
        private String name;
        private String address;
        private String roadAddress;
        private String phone;
        private double avgScore;
        private int totalReviews;
        private double longitude;
        private double latitude;
        private String placeUrl;
        private String categoryGroupCode;
    }
}
