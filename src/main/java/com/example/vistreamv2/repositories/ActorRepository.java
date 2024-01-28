package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByFullName(String name);
}
