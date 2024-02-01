package com.example.vistreamv2.dtos.requests.actor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ActorMultipleReqDto(
        @Valid @NotNull
        List<ActorReqDto> actors
) {
}
