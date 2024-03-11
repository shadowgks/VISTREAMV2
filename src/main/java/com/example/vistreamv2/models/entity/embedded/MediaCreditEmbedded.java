package com.example.vistreamv2.models.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaCreditEmbedded implements Serializable {
    @Column(name = "media_id")
    private Long idMedia;
    @Column(name = "credit_id")
    private Long idCredit;
}
