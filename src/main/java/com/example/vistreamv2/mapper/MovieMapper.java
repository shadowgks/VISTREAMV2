package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.response.movie.MovieResDto;
import com.example.vistreamv2.models.entity.Media;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {
    private final ModelMapper modelMapper;

    public MovieResDto mapToDto(Media media){
        return modelMapper.map(media, MovieResDto.class);
    }
}
