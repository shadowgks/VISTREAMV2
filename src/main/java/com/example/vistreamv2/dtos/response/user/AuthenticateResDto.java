package com.example.vistreamv2.dtos.response.user;

import lombok.Builder;

@Builder
public record AuthenticateResDto(
        String token,
        String firstName,
        String lastName,
        String email,
        String password
) {
}
