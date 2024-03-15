package com.example.vistreamv2.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "media_servers_episode")
public class MediaServerPlayEpisode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 5000)
    private String mediaPath;

    @ManyToOne
    @JoinColumn(name = "media_id")
    @JsonBackReference
    private Media media;
    @ManyToOne
    @JoinColumn(name = "server_id")
    private ServerPlay serverPlay;
    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;
}
