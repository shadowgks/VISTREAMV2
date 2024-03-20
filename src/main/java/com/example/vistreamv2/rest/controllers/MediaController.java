package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.mapper.MediaMapper;
import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.services.GenreService;
import com.example.vistreamv2.services.MediaService;
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

@RestController
@RequestMapping("/v1.0.0/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private final GenreService genreService;
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
                .map(mediaMapper::mapToShortMediaResDto)
                .toList();
        System.out.println(shortMediaResDtoList);

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
    public ResponseEntity<Response<Object>> findAllMediaRecommendedAndLatest(@RequestParam("column") Optional<String> column,
                                                                    @RequestParam("type_media") Optional<String> typeMedia,
                                                                    @RequestParam("limit_data") Optional<Integer> limitData){
        List<Media> mediaList = mediaService.findAllMediaRecommended(
                column.orElse("popularity"),
                typeMedia.orElse("movie"),
                limitData.orElse(12));
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(mediaList.stream()
                        .map(mediaMapper::mapToShortMediaResDto)
                        .toList())
                .build());
    }

    @GetMapping("/genre-or-country/{genreOrCountry}")
    public ResponseEntity<Response<Object>> findAllMediaGenre(@Valid
                                                              @PathVariable("genreOrCountry") String genreOrCountry,
                                                              @RequestParam Optional<Integer> numPage,
                                                              @RequestParam Optional<Integer> numSize){

        Object o;
        Genre genre = genreService.findByName(genreOrCountry);
        Map<String, Page<ShortMediaResDto>> stringListMap = new HashMap<>();
        // initialize pageable default
        Pageable pageable = PageRequest.of(
                numPage.orElse(0),
                numSize.orElse(10)
        );
        //Get data
        Page<Media> media = mediaService.findAllMediaByGenres(
                genre,
                pageable
        );
        //Mapped Data
        List<ShortMediaResDto> shortMediaResDtoList = media.stream()
                .map(mediaMapper::mapToShortMediaResDto)
                .toList();

        System.out.println(shortMediaResDtoList);
        // Insert data in PageImpl class
        Page<ShortMediaResDto> shortMediaResDtos = new PageImpl<>(shortMediaResDtoList, pageable, media.getTotalPages());
        stringListMap.put("page", shortMediaResDtos);
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(stringListMap)
                .build());
    }

}
