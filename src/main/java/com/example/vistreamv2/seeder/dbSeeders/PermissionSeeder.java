package com.example.vistreamv2.seeder.dbSeeders;

import com.example.vistreamv2.models.entity.Permission;
import com.example.vistreamv2.models.enums.ActionType;
import com.example.vistreamv2.models.enums.SubjectType;
import com.example.vistreamv2.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PermissionSeeder {
    private final PermissionRepository permissionRepository;

    private final Set<String> subjects = Set.of(
            "users",
            "media",
            "sliders"
    );
    Set<Permission> permissions = new HashSet<>();

    public void seed(){
        if (permissionRepository.findAll().isEmpty()) {
            for (SubjectType subjectType : SubjectType.values()) {
                for (ActionType actionType : ActionType.values()) {
                    Permission permission = Permission.builder()
                            .action(actionType)
                            .subject(subjectType.name())
                            .build();
                    permissions.add(permission);
                }
            }
            permissionRepository.saveAll(permissions);
        }
    }

    public Set<Permission> permissionsCalled(){
        return permissions;
    }

}
