package MuDuck.MuDuck.map.entity;


import MuDuck.MuDuck.map.groupcode.GroupCode;

public interface AvgEntity {
    long getMapId();
    long getPlaceId();
    String getPlaceName();
    String getAddress();
    String getRoadAddress();
    String getPhone();
    String getAvgScore();
    int getTotalReviews();
    double getLongitude();
    double getLatitude();
    String getPlaceUrl();
    GroupCode getCategoryGroupCode();
    int getRn();
}
