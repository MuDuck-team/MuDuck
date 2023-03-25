package MuDuck.MuDuck.map.groupcode;

import lombok.Getter;

public enum GroupCode {
    FD6("restaurants", "FD6"),
    CE7("cafes", "CE7"),
    PK6("parkings", "PK6");
    @Getter
    private final String name;
    @Getter
    private final String code;

    GroupCode(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
