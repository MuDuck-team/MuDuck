package MuDuck.MuDuck.theater.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TheaterDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response{
        private long id;
        private String placeName;
        private double longitude;
        private double latitude;
        private String phone;
        private String address;
        private String roadAddress;

        @Builder
        public Response(long id, String placeName, double longitude, double latitude,
                String phone,
                String address, String roadAddress) {
            this.id = id;
            this.placeName = placeName;
            this.longitude = longitude;
            this.latitude = latitude;
            this.phone = phone;
            this.address = address;
            this.roadAddress = roadAddress;
        }
    }
}
