package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

@Entity
public class ServerPlay {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media media;
}
