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
import jakarta.validation.Valid;
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
    private final MovieMapper movieMapper;
    private final MediaMapper mediaMapper;


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
                .result(mediaMapper.mapToMediaAlsoLikeDto(media, alsoLikes))
                .build());
    }


    @GetMapping("/recommended")
    public ResponseEntity<Response<Object>> findAllMediaRecommended(@RequestParam("type_media") Optional<String> typeMedia,
                                                                    @RequestParam("limit_data") Optional<Integer> limitData){
        List<Media> mediaList = mediaService.findAllMediaRecommended(typeMedia.orElse("movie"),
                limitData.orElse(12));
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(mediaList.stream()
                        .map(movieMapper::mapToDto)
                        .toList())
                .build());
    }
}
