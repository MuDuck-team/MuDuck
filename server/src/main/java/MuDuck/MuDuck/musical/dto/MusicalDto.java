package MuDuck.MuDuck.musical.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class MusicalDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @Positive
        @Setter
        private Long musicalId;
        @NotNull
        private String musicalKorName;
        @NotNull
        private String musicalEngName;
        @NotNull
        private String poster;
        @NotNull
        private String genre;
        @NotNull
        private String musicalInfo;
        @NotNull
        private String openDate;
        @NotNull
        private String closeDate;
        @NotNull
        private Integer age;
    }

    @Getter
    public static class Patch {

        private Long musicalId;
        private String musicalKorName;
        private String musicalEngName;
        private String poster;
        private String genre;
        private String musicalInfo;
        private String musicalState;
        private String openDate;
        private String closeDate;
        private Integer age;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private Long musicalId;
        private String musicalKorName;
        private String musicalEngName;
        private String poster;
        private String genre;
        private String musicalInfo;
        private String musicalState;
        private String openDate;
        private String closeDate;
        private Integer age;
        private Integer view;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
