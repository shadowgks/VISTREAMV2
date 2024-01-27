package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.requests.user.RegisterReqDto;
import com.example.vistreamv2.models.entity.AppUser;

public class UserRegisterMapper {
    public static AppUser mapToEntity(RegisterReqDto registerReqDto){
        return AppUser.builder()
                .email(registerReqDto.email())
                .password(registerReqDto.password())
                .userName(registerReqDto.userName())
                .lastName(registerReqDto.lastName())
                .firstName(registerReqDto.firstName())
                .roles(registerReqDto.roles())
                .build();
    }
}
