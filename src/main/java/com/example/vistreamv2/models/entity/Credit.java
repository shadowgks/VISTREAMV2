package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long idTmdb;
    private Boolean adult;
    private Integer gender;
    private String name;
    private Double popularity;
    private String profilePath;
}
