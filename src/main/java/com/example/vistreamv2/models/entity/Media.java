package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

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
    private Integer duration;
    private String posterPath;
    private String backDropPath;
    private String status;
    private LocalDate releaseDate;
    @Column(length = 10000)
    private String overview;
    @Column(unique = true)
    private String shortLink;
    private String originalLanguage;
    private Integer levelView;
    private Boolean adult;
    private Double popularity;
    private Double voteAverage;
    private Integer voteCount;
    private String typeMedia;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "media-genres",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "media-countries",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> countries;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "media-productions",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "production_id")
    )
    private Set<Production> productions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "media-videos",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<Videos> videos;

    @OneToMany(mappedBy = "media")
    private Set<Season> seasons;

    @OneToMany(mappedBy = "media")
    private Set<Watchlist> watchlists;

    @OneToMany(mappedBy = "media")
    private Set<MediaServerPlayEpisode> serverPlays;

    @OneToMany(mappedBy = "media")
    private Set<MediaCredit> credits;

    @ManyToOne
    private Slider slider;

    @ManyToOne
    private TypeQuality typeQuality;

}
