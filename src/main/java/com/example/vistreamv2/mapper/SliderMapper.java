package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.dtos.response.slider.SliderResDto;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.models.entity.MediaSlider;
import com.example.vistreamv2.models.entity.Slider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SliderMapper {
    private final ModelMapper modelMapper;

    public SliderResDto mapToDto(Slider slider){
        SliderResDto dto =  modelMapper.map(slider, SliderResDto.class);

        Set<Media> mediaList = slider.getMediaSet().stream()
                .map(MediaSlider::getMedia)
                .collect(Collectors.toSet());

        dto.setMediaSet(mediaList.stream()
                .map(media -> modelMapper.map(media, ShortMediaResDto.class))
                .collect(Collectors.toSet()));

        return dto;
    }
}
