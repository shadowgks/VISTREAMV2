package com.example.vistreamv2.dtos.response.user;

import com.example.vistreamv2.dtos.response.role.RoleResDto;
import com.example.vistreamv2.models.entity.PermissionGroup;
import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.entity.Watchlist;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeResourceResDTO {
    String firstName;
    String lastName;
    String userNamee;
    String email;
    LocalDateTime accessionDate;
    Set<RoleResDto> roles;
}
