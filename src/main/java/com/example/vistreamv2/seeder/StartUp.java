package com.example.vistreamv2.seeder;

import com.example.vistreamv2.seeder.dbSeeders.*;
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
    private final GenreSeeder genreSeeder;
    private final CountrySeeder countrySeeder;
    private final MovieSeeder movieSeeder;

    @Override
    public void run(String... args) throws Exception {
        //seed data manual
        permissionSeeder.seed();
        roleSeeder.seed();
        groupPermissionSeeder.seed();
        userSeeder.seed();

        //seed data from api
        genreSeeder.fetchAndSaveGenre();
//        countrySeeder.fetchAndSaveCountries();
        movieSeeder.fetchMediaTmdb();
    }
}
