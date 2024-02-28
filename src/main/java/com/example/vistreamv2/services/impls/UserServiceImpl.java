package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.repositories.UserRepository;
import com.example.vistreamv2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public List<AppUser> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findByUsernameUser(String username) {
        return userRepository.findByUserName(username)
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
                .isEnabled(true)
                .build();
        return userRepository.save(user);
    }

    @Override
    public AppUser authenticate(AppUser req) {
        AppUser user = userRepository.findByEmailOrUserName(req.getEmail(), req.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Not Found Any User"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        return user;
    }

    @Override
    public void updateUser(AppUser user) {
    }

    @Override
    public void deleteUser(Long username) {

    }
}
