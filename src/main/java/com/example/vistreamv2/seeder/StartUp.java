package com.example.vistreamv2.seeder;

import com.example.vistreamv2.seeder.dbSeeders.GroupPermissionSeeder;
import com.example.vistreamv2.seeder.dbSeeders.PermissionSeeder;
import com.example.vistreamv2.seeder.dbSeeders.RoleSeeder;
import com.example.vistreamv2.seeder.dbSeeders.UserSeeder;
import com.example.vistreamv2.services.UserService;
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
    private final UserSeeder userSeeder;
    private final RoleSeeder roleSeeder;
    private final PermissionSeeder permissionSeeder;
    private final GroupPermissionSeeder groupPermissionSeeder;

    @Override
    public void run(String... args) throws Exception {
//        if(roleService.findAllRole().isEmpty()){
//            roleService.createRole(new Role(null, Assignments.ADMIN));
//            roleService.createRole(new Role(null, Assignments.USER));
//            roleService.createRole(new Role(null, Assignments.SUPER_ADMIN));
//        }
//
//        if(userService.findAllUser().isEmpty()){
//            Set<Role> admin = new HashSet<>();
//            admin.add(roleService.findByAssignmentsRole(Assignments.ADMIN));
//            Set<Role> user = new HashSet<>();
//            user.add(roleService.findByAssignmentsRole(Assignments.USER));
//            Set<Role> superAdmin = new HashSet<>();
//            superAdmin.add(roleService.findByAssignmentsRole(Assignments.SUPER_ADMIN));
//
////            userService.create(new AppUser(null, "saad", "moumou", "saadmomo4", "saad@gmail.com", "saad1234", admin));
////            userService.create(new AppUser(null, "issam", "moumou", "issammomo4", "issam@gmail.com", "issam1234", superAdmin));
////            userService.create(new AppUser(null, "bilal","moumou", "bilalmomo4", "bilal@gmail.com", "bilal1234", user));
//        }
//        if (genreService.findAllGenre().isEmpty()) {
//            for (TypeGenre name : TypeGenre.values()){
//                genreService.createGenre(new Genre(null, name));
//            }
//        }

        permissionSeeder.seed();
        roleSeeder.seed();
        groupPermissionSeeder.seed();
        userSeeder.seed();




    }
}
