package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.Assignments;
import com.example.vistreamv2.repositories.RoleRepository;
import com.example.vistreamv2.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByAssignments(Assignments assignments) {
        return roleRepository.findByAssignments(assignments);
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Role role) {

    }
}
