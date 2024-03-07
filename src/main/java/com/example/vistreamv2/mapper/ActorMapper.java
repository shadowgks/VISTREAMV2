package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.requests.actor.ActorReqDto;
import com.example.vistreamv2.dtos.response.actor.ActorResDto;
import com.example.vistreamv2.models.entity.Actor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActorMapper {
    private final ModelMapper modelMapper;

    public Actor mapToEntity(ActorReqDto reqDto){
        return modelMapper.map(reqDto, Actor.class);

    }
    public ActorResDto mapToDto(Actor actor){
        return modelMapper.map(actor, ActorResDto.class);
    }
}
