package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.dtos.requests.actor.ActorReqDto;
import com.example.vistreamv2.dtos.response.actor.ActorResDto;
import com.example.vistreamv2.services.ActorService;
import com.example.vistreamv2.services.impls.ActorServiceImpl;
import com.example.vistreamv2.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0.0/actor")
public class ActorController {
    private final ActorService actorService;

    public EntityResponse<Response<ActorResDto>> createActor(@Valid @RequestBody ActorReqDto reqDto){
        Response<ActorResDto> response = new Response<>();


    }
}
