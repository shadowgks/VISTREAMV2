package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    List<AppUser> findAll();
    AppUser findByUsername(String username);
    AppUser register(AppUser user);
    AppUser authenticate(AppUser user);
    void update(AppUser user);
    void delete(Long username);

}
