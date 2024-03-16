package com.example.vistreamv2.dtos.response.media;

import com.example.vistreamv2.models.entity.TypeQuality;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlsoLikeResDto {
    private Long idTmdb;
    private String idImdb;
    private String title;
    private String originalTitle;
    private Integer duration;
    private String posterPath;
    private String backDropPath;
    private String status;
    private LocalDate releaseDate;
    private String overview;
    private String shortLink;
    private String originalLanguage;
    private Boolean adult;
    private Double popularity;
    private Double voteAverage;
    private Integer voteCount;
    private String typeMedia;
    private TypeQuality typeQuality;
}
