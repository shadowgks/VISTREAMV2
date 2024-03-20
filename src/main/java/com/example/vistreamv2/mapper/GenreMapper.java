package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.response.genre.GenreResDto;
import com.example.vistreamv2.models.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreMapper {
    private final ModelMapper modelMapper;

    public GenreResDto mapToDto(Genre genre){
        return modelMapper.map(genre, GenreResDto.class);
    }
}
