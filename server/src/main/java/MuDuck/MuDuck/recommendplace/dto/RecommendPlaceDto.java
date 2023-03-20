package MuDuck.MuDuck.recommendplace.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class RecommendPlaceDto {
    public static class Post{
        @NotNull
        @Positive
        private long memberId;
        @NotNull
        @Positive
        private long mapId;
        @NotNull
        @Pattern(regexp = "/^[0-9]{1}\\.{1}[0-9]{1}/", message = "올바른 별점을 입력해주세요")
        private double score;
        @NotNull
        @Length(min = 3, max = 50)
        private String oneLine;
    }

    public static class Patch{
        @Positive
        private long memberId;
        @Positive
        private long mapId;
        @Pattern(regexp = "/^[0-9]{1}\\.{1}[0-9]{1}/", message = "올바른 별점을 입력해주세요")
        private double score;
        @Length(min = 3, max = 50)
        private String oneLine;
    }

    public static class Response{
        private long id;
        private long memberId;
        private long mapId;
        private double score;
        private String oneLine;
    }
}
