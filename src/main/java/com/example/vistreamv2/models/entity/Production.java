package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "media_production",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "production_id")
    )
    private List<Media> mediaList;
}
