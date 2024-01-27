package com.example.vistreamv2.seeder;

import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.Assignments;
import com.example.vistreamv2.services.AppUserService;
import com.example.vistreamv2.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class fakeData implements CommandLineRunner {
    private final AppUserService userService;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if(roleService.findAll().isEmpty()){
            roleService.create(new Role(null, Assignments.ADMIN));
            roleService.create(new Role(null, Assignments.USER));
            roleService.create(new Role(null, Assignments.SUPER_ADMIN));
        }

        if(userService.findAll().isEmpty()){
            Set<Role> admin = new HashSet<>();
            admin.add(roleService.findByAssignments(Assignments.ADMIN));
            Set<Role> user = new HashSet<>();
            user.add(roleService.findByAssignments(Assignments.USER));
            Set<Role> superAdmin = new HashSet<>();
            superAdmin.add(roleService.findByAssignments(Assignments.SUPER_ADMIN));

//            userService.create(new AppUser(null, "saad", "moumou", "saadmomo4", "saad@gmail.com", "saad1234", admin));
//            userService.create(new AppUser(null, "issam", "moumou", "issammomo4", "issam@gmail.com", "issam1234", superAdmin));
//            userService.create(new AppUser(null, "bilal","moumou", "bilalmomo4", "bilal@gmail.com", "bilal1234", user));
        }
    }
}
