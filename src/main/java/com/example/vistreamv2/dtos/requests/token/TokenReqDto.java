package com.example.vistreamv2.dtos.requests.token;

import jakarta.validation.constraints.NotNull;

public record TokenReqDto(
        @NotNull(message = "Refresh Token must not be null")
        String refreshToken
) {
}
