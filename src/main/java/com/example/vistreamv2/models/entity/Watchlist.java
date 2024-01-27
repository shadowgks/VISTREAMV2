package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @OneToOne
//    private User user;
    @ManyToMany
    private List<Media> mediaItems;
}
