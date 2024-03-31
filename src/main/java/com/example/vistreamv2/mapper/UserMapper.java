package com.example.vistreamv2.mapper;


import com.example.vistreamv2.dtos.requests.user.AuthenticateReqDto;
import com.example.vistreamv2.dtos.requests.user.RegisterReqDto;
import com.example.vistreamv2.dtos.response.media.DetailsMediaResDto;
import com.example.vistreamv2.dtos.response.user.MeResourceResDTO;
import com.example.vistreamv2.models.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public AppUser mapToEntityRegister(RegisterReqDto registerReqDto){
        return modelMapper.map(registerReqDto, AppUser.class);
    }

    public AppUser mapToEntityAuthenticated(AuthenticateReqDto authenticateReqDto){
//        modelMapper.typeMap(AuthenticateReqDto.class, AppUser.class).addMappings(mapper -> {
//            mapper.map(src -> src.getEmail(),
//                    AppUser::setEmail);
//            mapper.map(src -> src.getPassword(),
//                    AppUser::setPassword);
//        });

        return modelMapper.map(authenticateReqDto, AppUser.class);
    }

    public MeResourceResDTO mapToDtoMeResource(AppUser appUser){
        MeResourceResDTO dto = modelMapper.map(appUser, MeResourceResDTO.class);
        dto.getRoles().forEach(r -> r.getPermissions()
                .forEach(p -> p.setSubjectAction(p.getSubject() +":"+ p.getAction())));
        return dto;
    }

//    public UserResDto mapToDto(AppUser user){
//        return modelMapper.map(user, UserResDto.class);
//    }
}
