package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.mapper.SliderMapper;
import com.example.vistreamv2.models.entity.Slider;
import com.example.vistreamv2.services.SliderService;
import com.example.vistreamv2.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1.0.0/slider")
@RequiredArgsConstructor
public class SliderController {
    private final SliderMapper sliderMapper;
    private final SliderService sliderService;

    @GetMapping()
    public ResponseEntity<Response<Object>> findSliders(){
        List<Slider> sliders = sliderService.findAllSlider();
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(sliders.stream()
                        .map(sliderMapper::mapToDto)
                        .toList())
                .build());
    }

    @GetMapping("/{is_enabled}")
    public  ResponseEntity<Response<Object>> findSliderByIsEnabled(@PathVariable("is_enabled") Boolean value){
        Slider slider = sliderService.findSliderByIsEnabled(value);
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(sliderMapper.mapToDto(slider))
                .build());
    }

}
