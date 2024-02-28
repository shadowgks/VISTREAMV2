package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    List<Permission> findAllPermission();
    Permission createPermission(Permission permission);
}
