package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(RoleType name);
}
