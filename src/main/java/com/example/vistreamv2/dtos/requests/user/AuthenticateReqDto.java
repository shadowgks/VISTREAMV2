package com.example.vistreamv2.dtos.requests.user;


import jakarta.validation.constraints.NotNull;

public record AuthenticateReqDto(
        @NotNull(message = "Email cannot be null")
        String email,
        @NotNull(message = "Password cannot be null")
        String password
) { }
