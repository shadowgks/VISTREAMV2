package com.example.vistreamv2.dtos.response.permission;

import com.example.vistreamv2.models.enums.ActionType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResDto {
    private String subject;
    private ActionType action;
}