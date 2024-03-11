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
public class MediaCredit {
    @EmbeddedId
    private MediaCreditEmbedded id;
    @ManyToOne
    @MapsId("mediaId")
    @JoinColumn(name = "media_id")
    private Media media;
    @ManyToOne
    @MapsId("creditgId")
    @JoinColumn(name = "credit_id")
    private Credit credit;
    private Integer order;
}
