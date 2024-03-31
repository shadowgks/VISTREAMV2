package com.example.vistreamv2.dtos.response.user;

import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.dtos.response.role.RoleResDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeResourceResDTO {
    Boolean isEnabled;
    String firstName;
    String lastName;
    String userNamee;
    String email;
    LocalDateTime accessionDate;
    Set<RoleResDto> roles;
    Set<ShortMediaResDto> watchlists;
}
