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
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String iso;
    private String englishName;
    private String nativeName;

    @OneToMany(mappedBy = "country")
    private List<Media> mediaList;
}
