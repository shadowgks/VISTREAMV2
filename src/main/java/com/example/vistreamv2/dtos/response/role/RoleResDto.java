package com.example.vistreamv2.dtos.response.role;

import com.example.vistreamv2.dtos.response.permission.PermissionResDto;
import com.example.vistreamv2.models.entity.Permission;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResDto {
    String name;
    Set<PermissionResDto> permissions;
}
