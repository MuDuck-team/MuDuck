package MuDuck.MuDuck.recommendplace.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


public class RecommendPlaceDto {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class Post{
        @NotNull
        @Positive
        private long memberId;
        @DecimalMin(value = "0.0", message = "최소 0.0 이상으로 입력해주세요")
        @DecimalMax(value = "5.0", message = "최대 5.0 이하로 입력해주세요")
        private double score;
        @NotNull
        @Length(min = 3, max = 50)
        private String oneLine;
    }
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Positive
        private long memberId;
        @Positive
        private long mapId;
        @DecimalMin(value = "0.0", message = "최소 0.0 이상으로 입력해주세요")
        @DecimalMax(value = "5.0", message = "최대 5.0 이하로 입력해주세요")
        private double score;
        @Length(min = 3, max = 50)
        private String oneLine;
    }
    @Getter
    @AllArgsConstructor
    public static class Response{
        private long id;
        private long memberId;
        private long mapId;
        private double score;
        private String oneLine;
    }
}
