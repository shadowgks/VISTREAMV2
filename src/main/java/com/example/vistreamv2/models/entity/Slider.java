package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private LocalDateTime date;
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isEnabled = false;

    @OneToMany(mappedBy = "slider")
    private Set<MediaSlider> mediaSet;
}
