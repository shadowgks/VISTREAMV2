package com.example.vistreamv2.dtos.requests.user;

import com.example.vistreamv2.models.entity.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record RegisterReqDto(
        String firstName,
        String lastName,
        String userName,
        String email,
        String password,
        Set<Role> roles
) {
}
