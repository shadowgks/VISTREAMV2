package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    List<AppUser> findAllUser();
    AppUser findByUsernameUser(String username);
    AppUser register(AppUser user);
    AppUser authenticate(AppUser user);
    void updateUser(AppUser user);
    void deleteUser(Long username);

}
