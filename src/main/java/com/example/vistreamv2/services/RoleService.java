package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService{
    List<Role> findAllRole();
    Role findByAssignmentsRole(Assignments assignments);
    Role createRole(Role role);
    void deleteRole(Long id);
    void updateRole(Role role);
}
