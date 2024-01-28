package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

@Entity
public class ServerPlay {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;
}
