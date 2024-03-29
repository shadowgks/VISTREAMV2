package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.response.media.AlsoLikeResDto;
import com.example.vistreamv2.dtos.response.media.DetailsMediaResDto;
import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.dtos.response.media.credit.MediaCreditResDto;
import com.example.vistreamv2.dtos.response.video.VideoResDto;
import com.example.vistreamv2.models.entity.Media;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MediaMapper {
    private final ModelMapper modelMapper;

    public DetailsMediaResDto mapToMediaAlsoLikeDto(Media media, Set<Media> alsoLikes){
        DetailsMediaResDto dto = modelMapper.map(media, DetailsMediaResDto.class);
        // Configure custom mapping for nested objects
        dto.setTrailers(media.getVideos().stream()
                .filter(trailer -> "Trailer".equals(trailer.get_type()))
                .map(video -> modelMapper.map(video, VideoResDto.class))
                .collect(Collectors.toSet()));
        dto.setAlsoLikes(alsoLikes.stream()
                .filter(likeMedia -> !media.getId().equals(likeMedia.getId()))
                .map(like -> modelMapper.map(like, AlsoLikeResDto.class))
                .limit(12)
                .collect(Collectors.toSet()));
        dto.setCredits(media.getCredits().stream()
                .map(credit -> modelMapper.map(credit, MediaCreditResDto.class))
                .toList());
        System.out.println(dto.getCredits());
        return dto;
    }

    public ShortMediaResDto mapToShortMediaResDto(Media media){
        return modelMapper.map(media, ShortMediaResDto.class);
    }

}
