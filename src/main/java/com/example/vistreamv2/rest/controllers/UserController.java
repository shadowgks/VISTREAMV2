package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.config.JwtService;
import com.example.vistreamv2.dtos.requests.user.AuthenticateReqDto;
import com.example.vistreamv2.dtos.requests.user.RegisterReqDto;
import com.example.vistreamv2.dtos.response.user.AuthenticateResDto;
import com.example.vistreamv2.mapper.user.UserAuthenticateMapper;
import com.example.vistreamv2.mapper.user.UserRegisterMapper;
import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.services.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0.0/auth")
@RequiredArgsConstructor
public class UserController {
    private final AppUserService userService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<AppUser>> findAllUser(){
        List<AppUser> userList = userService.findAllUser();
        return ResponseEntity.ok(userList);
    }
    @GetMapping("/{username}")
    public ResponseEntity<AppUser> findUserByUsername(@Valid @PathVariable("username") String username){
        AppUser user = userService.findByUsernameUser(username);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticateResDto> register(@Valid @RequestBody RegisterReqDto registerReqDto){
        AppUser user = userService.register(UserRegisterMapper.mapToEntity(registerReqDto));
        //generate new Token
        String jwtToken = jwtService.generateToken(user);
        return new ResponseEntity<>(UserAuthenticateMapper.mapToDto(user, jwtToken), HttpStatus.CREATED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResDto> authenticate(@Valid @RequestBody AuthenticateReqDto authenticateReqDto){
        AppUser user = userService.authenticate(UserAuthenticateMapper.mapToEntity(authenticateReqDto));
        //generate new Token
        String jwtToken = jwtService.generateToken(user);
        return new ResponseEntity<>(UserAuthenticateMapper.mapToDto(user, jwtToken), HttpStatus.OK);
    }
}
