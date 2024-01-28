package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUsers;
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
}
