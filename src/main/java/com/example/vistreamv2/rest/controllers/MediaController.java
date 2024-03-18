package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.mapper.MediaMapper;
import com.example.vistreamv2.mapper.MovieMapper;
import com.example.vistreamv2.mapper.SliderMapper;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.models.entity.Slider;
import com.example.vistreamv2.services.MediaService;
import com.example.vistreamv2.services.SliderService;
import com.example.vistreamv2.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1.0.0/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private final SliderService sliderService;
    private final MovieMapper movieMapper;
    private final MediaMapper mediaMapper;
    private final SliderMapper sliderMapper;

    @GetMapping
    public ResponseEntity<Response<Object>> getAllMedia(@RequestParam Optional<String> searchTerm,
                                                        @RequestParam Optional<String> typeMedia,
                                                        @RequestParam Optional<Integer> numPage,
                                                        @RequestParam Optional<Integer> numSize){
        Map<String, Page<ShortMediaResDto>> stringListMap = new HashMap<>();
        // initialize pageable default
        Pageable pageable = PageRequest.of(
                numPage.orElse(0),
                numSize.orElse(10)
        );
        // initialize request default
        Page<Media> mediaPage = mediaService.findAllMediaPageable(
                typeMedia.orElse(""),
                searchTerm.orElse(""),
                pageable
        );
        // Mapped data
        List<ShortMediaResDto> shortMediaResDtoList = mediaPage.stream()
                .map(movieMapper::mapToDto)
                .toList();

        // Insert data in PageImpl class
        Page<ShortMediaResDto> movieResDtoPage = new PageImpl<>(shortMediaResDtoList, pageable, mediaPage.getTotalElements());
        // Set data pageble
        stringListMap.put("page", movieResDtoPage);
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(stringListMap)
                .build());
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<Response<Object>> findMediaByShortLink(@PathVariable String shortLink){
        Media media = mediaService.findMediaByShortLink(shortLink);
        Set<Media> alsoLikes = mediaService.mediaAlsoLike(media.getCountries(), media.getGenres(), media.getProductions());
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(mediaMapper.mapToDto(media, alsoLikes))
                .build());
    }

    @GetMapping("/sliders")
    public  ResponseEntity<Response<Object>> slidersFind(){
        List<Slider> sliders = sliderService.findAllSlider();
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(sliders.stream()
                        .map(sliderMapper::mapToDto)
                        .toList())
                .build());
    }
}
