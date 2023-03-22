package MuDuck.MuDuck.musical.dto;

import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.Musical.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

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
        @NotNull
        private Integer runningTime;
        @NotNull
        private Integer intermission;
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
        private Integer runningTime;
        private Integer intermission;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class ResponseMusical{
        private Long id;
        private String musicalKorName;
        private String musicalEngName;
        private String poster;
        private Musical.Genre genre;
        private String musicalInfo;
        private Musical.MusicalState musicalState;
        private String openDate;
        private String closeDate;
        private Musical.Age age;
        private Integer runningTime;
        private Integer intermission;
        private List<ActorMusicalResponseDto.detail> actorMusicals;
        private Integer views;

        public String getGenre(){
            return genre.getDescription();
        }

        public String getMusicalState() {
            return musicalState.getStepDescription();
        }

        public String getAge(){
            return age.getStepDescription();
        }
    }

    @AllArgsConstructor
    @Getter
    @Builder
    @JsonPropertyOrder({"id", "musicalKorName", "poster"})
    public static class ResponseMusicals{
        @JsonProperty("id")
        private Long musicalId;
        private String musicalKorName;
        private String poster;
        @JsonProperty("actors")
        private List<ActorMusicalResponseDto.listing> actorMusicals;
    }

    @AllArgsConstructor
    @Getter
    public static class SingleResponseDto<T> {
        private T data;
    }

    @Getter
    public static class MultiResponseDto<T> {

        private List<T> musicals;
        private PageInfo pageInfo;

        public MultiResponseDto(List<T> data, Page page) {
            this.musicals = data;
            this.pageInfo = new PageInfo(page.getNumber() + 1,
                    page.getSize(), page.getTotalElements(), page.getTotalPages());
        }
    }

    @AllArgsConstructor
    @Getter
    public static class PageInfo {

        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
    }
}
