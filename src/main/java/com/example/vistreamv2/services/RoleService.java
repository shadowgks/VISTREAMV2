package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService{
    List<Role> findAll();
    Role findByAssignments(Assignments assignments);
    Role create(Role role);
    void delete(Long id);
    void update(Role role);
}
