package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.Slider;
import com.example.vistreamv2.repositories.SliderRepository;
import com.example.vistreamv2.services.SliderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SliderServiceImpl implements SliderService {
    private final SliderRepository sliderRepository;

    @Override
    public List<Slider> findAllSlider() {
        List<Slider> sliders = sliderRepository.findAll();
        return sliders;
    }
}
