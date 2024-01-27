package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Role;
import com.example.vistreamv2.models.enums.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAssignments(Assignments assignments);
}
