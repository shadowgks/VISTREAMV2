package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
