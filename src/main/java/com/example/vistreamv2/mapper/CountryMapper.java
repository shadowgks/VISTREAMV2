package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.response.country.CountryResDto;
import com.example.vistreamv2.dtos.response.genre.GenreResDto;
import com.example.vistreamv2.models.entity.Country;
import com.example.vistreamv2.models.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryMapper {
    private final ModelMapper modelMapper;

    public CountryResDto mapToDto(Country country){
        return modelMapper.map(country, CountryResDto.class);
    }
}
