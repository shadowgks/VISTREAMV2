package com.example.vistreamv2.dtos.response.episode;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeResDto {
    private String title;
    private Integer episodeNumber;
    private String picture;
}
