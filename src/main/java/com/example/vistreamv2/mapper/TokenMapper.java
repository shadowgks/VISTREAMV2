package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.requests.token.TokenReqDto;
import com.example.vistreamv2.dtos.response.token.TokenResDTO;
import com.example.vistreamv2.models.entity.RefreshToken;

public class TokenMapper {
    public static RefreshToken toEntity(TokenReqDto tokenReqDTO){
        return RefreshToken.builder()
                .token(tokenReqDTO.refreshToken())
                .build();
    }

    public static TokenResDTO toDto(RefreshToken refreshToken){
        return TokenResDTO.builder()
                .accessToken(refreshToken.getToken())
                .build();
    }
}
