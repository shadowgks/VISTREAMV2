package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.response.credit.CreditResDto;
import com.example.vistreamv2.dtos.response.media.DetailsMediaDto;
import com.example.vistreamv2.dtos.response.media.credit.MediaCreditResDto;
import com.example.vistreamv2.dtos.response.movie.MovieResDto;
import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.models.entity.MediaCredit;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MediaMapper {
    private final ModelMapper modelMapper;

    public DetailsMediaDto mapToDto(Media media){
        DetailsMediaDto dto = modelMapper.map(media, DetailsMediaDto.class);
        // Configure custom mapping for nested objects
        dto.setGenres(media.getGenres().stream().map(genre -> modelMapper.map(genre, Genre.class))
                .collect(Collectors.toSet()));
        dto.setMediaCredits(media.getMediaCredits().stream().map(credit -> modelMapper.map(credit, MediaCreditResDto.class))
                .collect(Collectors.toSet()));
        // Map other nested objects in a similar manner
        return dto;
    }
}
