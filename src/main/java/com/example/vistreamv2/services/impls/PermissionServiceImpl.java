package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.Permission;
import com.example.vistreamv2.repositories.PermissionRepository;
import com.example.vistreamv2.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    @Override
    public List<Permission> findAllPermission() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
}
