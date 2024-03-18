package com.example.vistreamv2.models.entity;

import com.example.vistreamv2.models.entity.embedded.MediaCreditEmbedded;
import com.example.vistreamv2.models.entity.embedded.MediaSliderEmbedded;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "media_sliders")
public class MediaSlider {
    @EmbeddedId
    private MediaSliderEmbedded id;
    @ManyToOne
    @MapsId("idMedia")
    @JoinColumn(name = "media_id")
    @JsonBackReference
    private Media media;
    @ManyToOne
    @MapsId("idSlider")
    @JoinColumn(name = "slider_id")
    private Slider slider;
    private String picture;
}
