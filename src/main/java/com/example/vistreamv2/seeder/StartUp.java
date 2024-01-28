package com.example.vistreamv2.seeder;

import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.Assignments;
import com.example.vistreamv2.models.enums.TypeGenre;
import com.example.vistreamv2.services.AppUserService;
import com.example.vistreamv2.services.GenreService;
import com.example.vistreamv2.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StartUp implements CommandLineRunner {
    private final AppUserService userService;
    private final RoleService roleService;
    private final GenreService genreService;

    @Override
    public void run(String... args) throws Exception {
        if(roleService.findAllRole().isEmpty()){
            roleService.createRole(new Role(null, Assignments.ADMIN));
            roleService.createRole(new Role(null, Assignments.USER));
            roleService.createRole(new Role(null, Assignments.SUPER_ADMIN));
        }

        if(userService.findAllUser().isEmpty()){
            Set<Role> admin = new HashSet<>();
            admin.add(roleService.findByAssignmentsRole(Assignments.ADMIN));
            Set<Role> user = new HashSet<>();
            user.add(roleService.findByAssignmentsRole(Assignments.USER));
            Set<Role> superAdmin = new HashSet<>();
            superAdmin.add(roleService.findByAssignmentsRole(Assignments.SUPER_ADMIN));

//            userService.create(new AppUser(null, "saad", "moumou", "saadmomo4", "saad@gmail.com", "saad1234", admin));
//            userService.create(new AppUser(null, "issam", "moumou", "issammomo4", "issam@gmail.com", "issam1234", superAdmin));
//            userService.create(new AppUser(null, "bilal","moumou", "bilalmomo4", "bilal@gmail.com", "bilal1234", user));
        }
        if (genreService.findAllGenre().isEmpty()) {
            for (TypeGenre name : TypeGenre.values()){
                genreService.createGenre(new Genre(null, name));
            }
        }
    }
}
