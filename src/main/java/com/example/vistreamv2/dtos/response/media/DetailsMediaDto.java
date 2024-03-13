package com.example.vistreamv2.dtos.response.media;

import com.example.vistreamv2.dtos.response.country.CountryResDto;
import com.example.vistreamv2.dtos.response.genre.GenreResDto;
import com.example.vistreamv2.dtos.response.media.credit.MediaCreditResDto;
import com.example.vistreamv2.dtos.response.production.ProductionResDto;
import com.example.vistreamv2.dtos.response.video.VideoResDto;
import com.example.vistreamv2.models.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailsMediaDto {
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
    private Set<GenreResDto> genres;
    private Set<CountryResDto> countries;
    private Set<ProductionResDto> productions;
    private Set<VideoResDto> videos;
    private Set<MediaCreditResDto> credits;
    private Set<Season> seasons;
    private Set<Watchlist> watchlists;
    private Set<ServerPlay> serverPlays;
    private Set<Slider> sliders;
}
