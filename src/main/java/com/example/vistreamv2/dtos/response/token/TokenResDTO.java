package com.example.vistreamv2.dtos.response.token;

import com.example.vistreamv2.models.entity.RefreshToken;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResDTO {
    String accessToken;
    String refreshToken;
}
