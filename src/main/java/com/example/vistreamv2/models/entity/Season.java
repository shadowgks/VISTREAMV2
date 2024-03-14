package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long idTmdb;
    private String name;
    private Integer seasonNumber;
    private String picture;
    private Double voteAverage;
    private LocalDate airDate;
    private Integer episodeCount;
    private String overview;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @OneToMany(mappedBy = "season")
    private Set<MediaServerPlay> serverPlays;

    @OneToMany(mappedBy = "season")
    private Set<Episode> episodes;
}