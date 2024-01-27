package com.example.vistreamv2.dtos.response.user;

import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.Assignments;
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
