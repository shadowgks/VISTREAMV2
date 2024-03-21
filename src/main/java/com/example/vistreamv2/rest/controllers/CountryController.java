package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.mapper.CountryMapper;
import com.example.vistreamv2.mapper.GenreMapper;
import com.example.vistreamv2.models.entity.Country;
import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.services.CountryService;
import com.example.vistreamv2.services.GenreService;
import com.example.vistreamv2.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0.0/country")
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;
    @GetMapping
    public ResponseEntity<Response<Object>> findGenres(){
        List<Country> countries = countryService.findAllCountry();
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(countries.stream()
                        .map(countryMapper::mapToDto)
                        .toList())
                .build());
    }
}
