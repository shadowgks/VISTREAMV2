//package com.example.vistreamv2.models.entity;
//
//import com.example.vistreamv2.models.entity.embedded.MediaSeasonEpisodeEmbedded;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Table(name = "media_season_episode")
//public class MediaSeasonEpisode {
//    @EmbeddedId
//    private MediaSeasonEpisodeEmbedded id;
//
//    @ManyToOne
//    @MapsId("idServer")
//    @JoinColumn(name = "server_id")
//    private Episode serverPlay;
//    @ManyToOne
//    @MapsId("idServer")
//    @JoinColumn(name = "server_id")
//    private Media serverPlay;
//    @ManyToOne
//    @MapsId("idServer")
//    @JoinColumn(name = "server_id")
//    private ServerPlay serverPlay;
//}
