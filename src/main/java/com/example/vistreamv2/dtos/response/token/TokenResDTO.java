package com.example.vistreamv2.dtos.response.token;

import lombok.Builder;

@Builder
public record TokenResDTO(
        String accessToken,
        String refreshToken
){}
