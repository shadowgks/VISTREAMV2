package com.example.vistreamv2.seeder.dbSeeders;

import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.models.entity.Permission;
import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.ActionType;
import com.example.vistreamv2.models.enums.RoleType;
import com.example.vistreamv2.repositories.RoleRepository;
import com.example.vistreamv2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;
    private final PermissionSeeder permissionSeeder;



    public void seed(){
        Set<Permission> permissionSet = permissionSeeder.permissionsCalled();
        if (roleRepository.findAll().isEmpty()){
            for (RoleType roleType : RoleType.values()) {
                Role role = Role.builder().build();
                role.setName(roleType);
                role.setPermissions(permissionSet.stream().filter(p -> p.getAction().equals(ActionType.ALL)).collect(Collectors.toSet()));
                if(roleType.equals(RoleType.GUEST)){
                    role.setPermissions(permissionSet.stream().filter(p -> p.getAction().equals(ActionType.READ)).collect(Collectors.toSet()));
                }
                roleRepository.save(role);
            }
        }

    }
}
