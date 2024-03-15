package com.example.vistreamv2.dtos.response.media.serverPlay;

import com.example.vistreamv2.dtos.response.episode.EpisodeResDto;
import com.example.vistreamv2.dtos.response.season.SeasonResDto;
import com.example.vistreamv2.dtos.response.serverPlay.ServerPlayResDto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaServerPlayEpisodeResDto {
    private String mediaPath;
    private ServerPlayResDto serverPlay;
    private EpisodeResDto episode;
}
