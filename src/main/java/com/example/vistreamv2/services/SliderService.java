package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Slider;

import java.util.List;
import java.util.Set;


public interface SliderService {
    List<Slider> findAllSlider();
    Slider findSliderByIsEnabled(Boolean value);
}
