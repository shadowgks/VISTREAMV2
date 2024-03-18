package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.models.entity.Media;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {
    private final ModelMapper modelMapper;

    public ShortMediaResDto mapToDto(Media media){
        return modelMapper.map(media, ShortMediaResDto.class);
    }
}
