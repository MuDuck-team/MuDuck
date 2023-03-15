package MuDuck.MuDuck.map.groupcode;

import lombok.Getter;

public enum GroupCode {
    PK6("주자장", "PK6"),
    FD6("음식점", "FD6"),
    CE7("카페", "CE7");
    @Getter
    private final String name;
    @Getter
    private final String code;

    GroupCode(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
