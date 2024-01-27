package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.requests.user.AuthenticateReqDto;
import com.example.vistreamv2.dtos.response.user.AuthenticateResDto;
import com.example.vistreamv2.models.entity.AppUser;

public class UserAuthenticateMapper {
    public static AuthenticateResDto mapToDto(AppUser user, String token){
        return AuthenticateResDto.builder()
                .token(token)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static AppUser mapToEntity(AuthenticateReqDto authenticateReqDto){
        return AppUser.builder()
                .email(authenticateReqDto.email())
                .password(authenticateReqDto.password())
                .build();
    }
}
