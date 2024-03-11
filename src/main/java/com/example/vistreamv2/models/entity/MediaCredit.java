package com.example.vistreamv2.models.entity;

import com.example.vistreamv2.models.entity.embedded.MediaCreditEmbedded;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "media_credit")
public class MediaCredit {
    @EmbeddedId
    private MediaCreditEmbedded id;
    @ManyToOne
    @MapsId("idMedia")
    @JoinColumn(name = "media_id")
    private Media media;
    @ManyToOne
    @MapsId("idCredit")
    @JoinColumn(name = "credit_id")
    private Credit credit;
    private String creditIdTmdb;
    private Integer order;
    private String character;
    private String knownForDepartment;
}
