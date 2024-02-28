package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
    Optional<PermissionGroup> findPermissionGroupByName(String name);
}
