package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String name;
    @Temporal(TemporalType.TIME)
    private Date duration;
    private String trailer;
    private String linkImage;
    private String director;
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(length = 5000)
    private String description;
    private String script;
    private String language;
    private boolean hasSubtitles;
    private boolean isAvailable;
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
    private List<ServerPlay> serverPlays;
}
