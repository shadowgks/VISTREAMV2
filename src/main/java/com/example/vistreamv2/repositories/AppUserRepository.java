package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String username);
    Optional<AppUser> findByEmail(String email);

}
