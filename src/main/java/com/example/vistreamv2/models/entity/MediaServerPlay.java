package com.example.vistreamv2.models.entity;

import com.example.vistreamv2.models.entity.embedded.MediaServerPlayEmbedded;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "media_servers")
public class MediaServerPlay {
    @EmbeddedId
    private MediaServerPlayEmbedded id;
    @ManyToOne
    @MapsId("idMedia")
    @JoinColumn(name = "media_id")
    @JsonBackReference
    private Media media;
    @ManyToOne
    @MapsId("idServer")
    @JoinColumn(name = "server_id")
    private ServerPlay credit;
    @ManyToOne
    @MapsId("idSeason")
    @JoinColumn(name = "season_id")
    private Season season;
    @ManyToOne
    @MapsId("idEpisode")
    @JoinColumn(name = "episode_id")
    private Episode episode;
    @Column(length = 5000)
    private String mediaPath;
}
