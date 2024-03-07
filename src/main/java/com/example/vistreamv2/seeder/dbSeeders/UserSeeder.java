package com.example.vistreamv2.seeder.dbSeeders;


import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.models.entity.Permission;
import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.ActionType;
import com.example.vistreamv2.models.enums.RoleType;
import com.example.vistreamv2.repositories.PermissionRepository;
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
public class UserSeeder {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void seed(){
        if(userRepository.findAll().isEmpty()){
            AppUser user = AppUser.builder()
                    .firstName("saad")
                    .lastName("momo")
                    .userNamee("saadmomo")
                    .email("saad@super.com")
                    .accessionDate(LocalDateTime.now())
                    .password(passwordEncoder.encode("12345678999"))
                    .isEnabled(true)
                    .build();
            roleRepository.findRoleByName(RoleType.SUPER_ADMIN)
                    .ifPresent(role -> user.setRoles(Set.of(role)));
            userRepository.save(user);
        }
    }
}
