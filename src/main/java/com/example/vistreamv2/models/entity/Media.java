package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private String title;
    private String originalTitle;
    private LocalDate duration;
    private String posterPath;
    private String backDropPath;
    private String linkTrailer;
    private String linkImdb;
    private String director;
    private LocalDate releaseDate;
    private String description;
    private String shortLink;
    private String originalLanguage;
    private boolean statusSerie;
    private Integer levelView;
    private Boolean adult;
    private Double popularity;
    private Double voteAverage;
    private Integer voteCount;
    private boolean isMovie;


    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "media")
    private List<Season> seasons;

    @OneToMany(mappedBy = "media")
    private List<Watchlist> watchlists;

    @OneToMany(mappedBy = "media")
    private List<ServerPlay> serverPlays;

    @ManyToOne
    private TypeQuality typeQuality;

    @OneToOne
    private Slider slider;
}
