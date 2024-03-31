package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.dtos.requests.media.FilterMediaReq;
import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.mapper.MediaMapper;
import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.models.entity.Country;
import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.repositories.MediaRepository;
import com.example.vistreamv2.services.CountryService;
import com.example.vistreamv2.services.GenreService;
import com.example.vistreamv2.services.MediaService;
import com.example.vistreamv2.services.UserService;
import com.example.vistreamv2.utils.Response;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/v1.0.0/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private final GenreService genreService;
    private final CountryService countryService;
    private final UserService userService;
    private final MediaMapper mediaMapper;

    private final MediaRepository mediaRepository;

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
                typeMedia.orElse(null), // Pass null if typeMedia is not provided
                searchTerm.orElse(""),
                pageable
        );
        // Mapped data
        List<ShortMediaResDto> shortMediaResDtoList = mediaPage.stream()
                .map(mediaMapper::mapToShortMediaResDto)
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
    public ResponseEntity<Response<Object>> findAllMediaByGenreOrCountry(@Valid
                                                              @PathVariable("genreOrCountry") String genreOrCountry,
                                                              @RequestParam Optional<Integer> numPage,
                                                              @RequestParam Optional<Integer> numSize){

        String name = null;
        Genre genre = genreService.findGenreByName(genreOrCountry);
        Country country = countryService.findCountryByName(genreOrCountry);
        if (genre == null && country == null) {
            throw new IllegalArgumentException("this name "+genreOrCountry+" not found in countries and genres");
        }
        if(genre == null){
            name = country.getName();
        }else if (country == null){
            name = genre.getName();
        }

        Map<String, Page<ShortMediaResDto>> stringListMap = new HashMap<>();
        // initialize pageable default
        Pageable pageable = PageRequest.of(
                numPage.orElse(0),
                numSize.orElse(10)
        );
        //Get data
        Page<Media> media = mediaService.findAllMediaByGenresOrCountry(
                name,
                pageable
        );
        //Mapped Data
        List<ShortMediaResDto> shortMediaResDtoList = media.stream()
                .map(mediaMapper::mapToShortMediaResDto)
                .toList();

        // Insert data in PageImpl class
        Page<ShortMediaResDto> shortMediaResDtos = new PageImpl<>(shortMediaResDtoList, pageable, media.getTotalPages());
        stringListMap.put("page", shortMediaResDtos);
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(stringListMap)
                .build());
    }

//    @GetMapping("test")
//    public ResponseEntity<?> watchList(){
//        Page<Media> media = mediaRepository.findWatchlistByUserId(1L, Pageable.ofSize(1));
//        return ResponseEntity.ok(media);
//    }

    @GetMapping("/watchlist")
    public ResponseEntity<Response<Object>> watchList(@RequestParam Optional<Integer> numPage,
                                                        @RequestParam Optional<Integer> numSize){
        Map<String, Page<ShortMediaResDto>> stringListMap = new HashMap<>();
        // initialize pageable default
        Pageable pageable = PageRequest.of(
                numPage.orElse(0),
                numSize.orElse(10)
        );
        // initialize request default
        Page<Media> mediaPage = mediaService.watchListPageable(userService.me().getId(), pageable);

        // Mapped data
        List<ShortMediaResDto> shortMediaResDtoList = mediaPage.stream()
                .map(mediaMapper::mapToShortMediaResDto)
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





//    @GetMapping("/filter")
//    public ResponseEntity<Response<Object>> filterMedia(@Valid @RequestBody FilterMediaReq filterMediaReq,
//                                                        @RequestParam Optional<Integer> numPage,
//                                                        @RequestParam Optional<Integer> numSize){
//        //get data
//        List<Country> countries = filterMediaReq.getCountries();
//        List<Genre> genres = filterMediaReq.getGenres();
//        LocalDate releasedDate = filterMediaReq.getReleasedDate();
//        String typeMedia = filterMediaReq.getTypeMedia();
//
//        Map<String, Page<ShortMediaResDto>> stringListMap = new HashMap<>();
//        // initialize pageable default
//        Pageable pageable = PageRequest.of(
//                numPage.orElse(0),
//                numSize.orElse(10)
//        );
//
//        // initialize request default
//        Page<Media> mediaPage = mediaService.filterMedia(typeMedia, releasedDate, countries, genres, pageable);
//
//        // Mapped data
//        List<ShortMediaResDto> shortMediaResDtoList = mediaPage.stream()
//                .map(mediaMapper::mapToShortMediaResDto)
//                .toList();
//
//        // Insert data in PageImpl class
//        Page<ShortMediaResDto> movieResDtoPage = new PageImpl<>(shortMediaResDtoList, pageable, mediaPage.getTotalElements());
//        // Set data pageble
//        stringListMap.put("page", movieResDtoPage);
//        return ResponseEntity.ok(Response.builder()
//                .message("Success")
//                .result(stringListMap)
//                .build());
//
//    }

}
