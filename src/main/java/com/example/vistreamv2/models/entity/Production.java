package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long idTmdb;
    private String name;
    private String logoPath;
    private String originCountry;

    @ManyToMany
    @JoinTable(
            name = "media_production",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "production_id")
    )
    private List<Media> mediaList;
}
