package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Slider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SliderRepository extends JpaRepository<Slider, Long> {
    Optional<Slider> findByIsEnabled(Boolean value);
}
