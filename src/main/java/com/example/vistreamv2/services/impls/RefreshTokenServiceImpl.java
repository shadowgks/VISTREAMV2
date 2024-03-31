package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.config.JwtService;
import com.example.vistreamv2.exception.custom.TokenException;
import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.models.entity.RefreshToken;
import com.example.vistreamv2.repositories.RefreshTokenRepository;
import com.example.vistreamv2.repositories.UserRepository;
import com.example.vistreamv2.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Value("${spring.jwt.refresh.expirationInMonths}")
    private Long EXPIRATION_REFRESH_TOKEN;
    @Override
    public RefreshToken createRefreshToken(Long idUser) {
        AppUser user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        LocalDateTime refreshTokenExpirationDateTime = LocalDateTime.now().plusMonths(EXPIRATION_REFRESH_TOKEN);
        RefreshToken refreshToken = RefreshToken.builder()
                .token(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()))
                .user(user)
                .revoked(false)
                .expiryDate(refreshTokenExpirationDateTime)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token == null){
            throw new TokenException("Token is null");
        }
        if(token.getExpiryDate().isBefore(LocalDateTime.now())){
            refreshTokenRepository.delete(token);
            throw new TokenException("Refresh token was expired. Please make a new authentication request");
        }
        return token;
    }

    @Override
    public RefreshToken generateNewToken(RefreshToken refreshToken) {
        AppUser user = refreshTokenRepository.findByToken(refreshToken.getToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .orElseThrow(() -> new TokenException("Refresh token does not exist"));
        String token = jwtService.generateAccessToken(user);
        return RefreshToken.builder()
                .token(token)
                .build();
    }
}
