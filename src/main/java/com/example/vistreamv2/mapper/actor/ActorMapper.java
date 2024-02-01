package com.example.vistreamv2.mapper.actor;

import com.example.vistreamv2.dtos.requests.actor.ActorReqDto;
import com.example.vistreamv2.dtos.response.actor.ActorResDto;
import com.example.vistreamv2.models.entity.Actor;


public class ActorMapper {
    public static Actor mapToEntity(ActorReqDto reqDto){
        return Actor.builder()
                    .fullName(reqDto.nameActor())
                    .birthDate(reqDto.dateBirthday())
                    .build();

    }
    public static ActorResDto mapToDto(Actor actor){
        return ActorResDto.builder()
                .name(actor.getFullName())
                .dateBirthday(actor.getBirthDate())
                .mediaList(actor.getMediaList())
                .build();
    }
}
