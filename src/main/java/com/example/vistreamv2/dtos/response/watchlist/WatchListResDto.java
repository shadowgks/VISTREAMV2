package com.example.vistreamv2.dtos.response.watchlist;

import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.models.entity.Media;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WatchListResDto {
    ShortMediaResDto media;
}
