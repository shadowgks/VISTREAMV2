package com.example.vistreamv2.models.entity;

import com.example.vistreamv2.models.enums.TypeGenre;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeGenre name;
    @ManyToMany
    @JoinTable(
            name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Media> mediaList;

    public Genre(Long id, TypeGenre name){
        this.id = id;
        this.name = name;
    }
}