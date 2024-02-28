package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Long idUser);
    RefreshToken verifyExpiration(RefreshToken token);
    RefreshToken generateNewToken(RefreshToken token);
}
