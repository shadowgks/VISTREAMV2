package com.example.vistreamv2.models.entity;

import com.example.vistreamv2.models.enums.TypeGenre;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeGenre name;
    @ManyToMany
    @JoinTable(
            name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Media> mediaList;
}