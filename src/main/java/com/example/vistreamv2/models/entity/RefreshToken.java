package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiryDate;
    public Boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;
}
