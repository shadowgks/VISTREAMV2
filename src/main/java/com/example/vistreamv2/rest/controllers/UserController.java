package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.config.JwtService;
import com.example.vistreamv2.dtos.requests.token.TokenReqDto;
import com.example.vistreamv2.dtos.requests.user.AuthenticateReqDto;
import com.example.vistreamv2.dtos.requests.user.RegisterReqDto;
import com.example.vistreamv2.dtos.response.token.TokenResDTO;
import com.example.vistreamv2.mapper.TokenMapper;
import com.example.vistreamv2.mapper.UserMapper;
import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.models.entity.RefreshToken;
import com.example.vistreamv2.services.RefreshTokenService;
import com.example.vistreamv2.services.UserService;
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
    private final UserService userService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final TokenMapper tokenMapper;
    private final UserMapper userMapper;



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
    public ResponseEntity<TokenResDTO> register(@Valid @RequestBody RegisterReqDto registerReqDto){
        AppUser user = userService.register(userMapper.mapToEntityRegister(registerReqDto));
        //generate new Token
        String jwtAccessToken = jwtService.generateAccessToken(user);
        RefreshToken jwtRefreshToken = refreshTokenService.createRefreshToken(user.getId());

        return new ResponseEntity<>(tokenMapper.toDto(jwtAccessToken, jwtRefreshToken.getToken()), HttpStatus.CREATED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<TokenResDTO> authenticate(@Valid @RequestBody AuthenticateReqDto authenticateReqDto){
        AppUser user = userService.authenticate(userMapper.mapToEntityAuthenticated(authenticateReqDto));
        //generate new Token
        String jwtAccessToken = jwtService.generateAccessToken(user);
        RefreshToken jwtRefreshToken = refreshTokenService.createRefreshToken(user.getId());
        return new ResponseEntity<>(tokenMapper.toDto(jwtAccessToken, jwtRefreshToken.getToken()) , HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResDTO> refreshToken(@RequestBody TokenReqDto request) {
        RefreshToken accessToken = refreshTokenService.generateNewToken(TokenMapper.toEntity(request));
        return ResponseEntity.ok(tokenMapper.toDto(accessToken.getToken(), request.refreshToken()));
    }
//    @GetMapping
//    public ResponseEntity<ToRDto>
}
