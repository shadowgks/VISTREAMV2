package com.example.vistreamv2.dtos.response.actor;

import java.time.LocalDate;

public record ActorResDto(
    String name,
    LocalDate dateBirthday
) {
}
