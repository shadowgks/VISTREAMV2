package com.example.vistreamv2.dtos.requests.actor;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ActorReqDto(
        @NotNull(message = "name must not be null")
        String nameActor,
        @NotNull(message = "date birthday must not be null")
        LocalDate dateBirthday
) {
}
