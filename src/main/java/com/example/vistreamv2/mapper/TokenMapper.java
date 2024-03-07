package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.requests.token.TokenReqDto;
import com.example.vistreamv2.dtos.response.token.TokenResDTO;
import com.example.vistreamv2.models.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenMapper {
    private final ModelMapper modelMapper;
    public static RefreshToken toEntity(TokenReqDto tokenReqDTO){
        return RefreshToken.builder()
                .token(tokenReqDTO.refreshToken())
                .build();
    }

    public TokenResDTO toDto(String access, String refresh){
        return modelMapper.map(TokenResDTO.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .build(), TokenResDTO.class);
    }


}
