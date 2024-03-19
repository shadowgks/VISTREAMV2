package com.example.vistreamv2.dtos.response.slider;

import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SliderResDto {
    private String name;
    private LocalDateTime date;
    private Boolean isEnabled;
    private Set<ShortMediaResDto> mediaSet;
}
