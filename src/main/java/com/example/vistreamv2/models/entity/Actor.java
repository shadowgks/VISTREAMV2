package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Date birthDate;
    @ManyToMany
    @JoinTable(
            name = "media_actor",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Media> mediaList;
}
