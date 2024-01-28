package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.repositories.AppUserRepository;
import com.example.vistreamv2.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public List<AppUser> findAllUser() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findByUsernameUser(String username) {
        return appUserRepository.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Any user by this: " + username));
    }

    @Override
    public AppUser register(AppUser req) {
        AppUser user = AppUser.builder()
                .userName(req.getUsername())
                .email(req.getEmail())
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .roles(req.getRoles())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();
        return appUserRepository.save(user);
    }

    @Override
    public AppUser authenticate(AppUser req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        return appUserRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Not Found Any user"));
    }

    @Override
    public void updateUser(AppUser user) {
    }

    @Override
    public void deleteUser(Long username) {

    }
}
