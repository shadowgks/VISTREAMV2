package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

@Entity
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String picture;
}
