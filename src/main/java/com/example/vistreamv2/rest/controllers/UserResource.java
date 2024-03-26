package com.example.vistreamv2.rest.controllers;


import com.example.vistreamv2.dtos.response.user.MeResourceResDTO;
import com.example.vistreamv2.mapper.UserMapper;
import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.services.UserService;
import com.example.vistreamv2.utils.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0.0/user")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public Response<MeResourceResDTO> userResource() {
        AppUser user1 = userService.me();
        MeResourceResDTO meResource = userMapper.mapToDtoMeResource(user1);

        return Response.<MeResourceResDTO>builder()
                .result(meResource)
                .build();
    }

}
