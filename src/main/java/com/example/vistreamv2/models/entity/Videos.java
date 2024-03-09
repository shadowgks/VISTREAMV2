package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Videos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idTmdb;
    private String name;
    private String key;
    private String site;
    private Integer size;
    private String type;
    private String official;
    private LocalDateTime publishedAt;

    @ManyToOne
    private Media media;
}
