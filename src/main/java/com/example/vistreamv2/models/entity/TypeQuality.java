package com.example.vistreamv2.models.entity;

import com.example.vistreamv2.models.enums.TypeQualityMedia;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeQualityMedia name;
    @OneToMany(mappedBy = "typeQuality")
    private List<Media> mediaList;
}
