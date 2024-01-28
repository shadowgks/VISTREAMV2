package com.example.vistreamv2.dtos.response.actor;

import com.example.vistreamv2.models.entity.Media;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ActorResDto(
    String name,
    LocalDate dateBirthday,
    List<Media> mediaList
) {
}
