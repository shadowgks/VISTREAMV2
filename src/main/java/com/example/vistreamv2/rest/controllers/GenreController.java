package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.mapper.GenreMapper;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0.0/genre")
public class GenreController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;
    @GetMapping
    public ResponseEntity<Response<Object>> findGenres(){
        List<Genre> genres = genreService.findAllGenre();
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(genres.stream()
                        .map(genreMapper::mapToDto)
                        .toList())
                .build());
    }
}
