package com.example.vistreamv2.mapper;

import com.example.vistreamv2.dtos.requests.actor.ActorReqDto;
import com.example.vistreamv2.dtos.requests.credit.CreditReqDto;
import com.example.vistreamv2.dtos.response.actor.ActorResDto;
import com.example.vistreamv2.dtos.response.country.CountryResDto;
import com.example.vistreamv2.dtos.response.credit.CreditResDto;
import com.example.vistreamv2.models.entity.Actor;
import com.example.vistreamv2.models.entity.Credit;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditMapper {
    private final ModelMapper modelMapper;
    public Credit mapToEntity(CreditReqDto reqDto){
        return modelMapper.map(reqDto, Credit.class);

    }
    public CreditResDto mapToDto(Credit credit){
        return modelMapper.map(credit, CreditResDto.class);
    }
}
