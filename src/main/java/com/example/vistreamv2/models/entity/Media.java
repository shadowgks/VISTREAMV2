package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long idTmdb;
    @Column(nullable = false)
    private String idImdb;
    private String title;
    private String originalTitle;
    private LocalDate duration;
    private String posterPath;
    private String backDropPath;
    private String linkTrailer;
    private String director;
    private String status;
    private LocalDate releaseDate;
    private String overview;
    private UUID shortLink;
    private String originalLanguage;
//    private Boolean statusSerie;
    private Integer levelView;
    private Boolean adult;
    private Double popularity;
    private Double voteAverage;
    private Integer voteCount;
    private String typeMedia;


    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;

    @ManyToOne
    @JoinColumn(name = "videos_id")
    private Videos video;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @OneToMany(mappedBy = "media")
    private Set<Watchlist> watchlists;

    @OneToMany(mappedBy = "media")
    private Set<ServerPlay> serverPlays;

    @ManyToOne
    private TypeQuality typeQuality;

    @OneToOne
    private Slider slider;


}
