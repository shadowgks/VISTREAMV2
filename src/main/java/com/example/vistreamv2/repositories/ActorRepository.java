package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query("SELECT m FROM Actor m " +
            "WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Optional<Page<Actor>> findActorByContaining(@Param("searchTerm") String searchTerm, Pageable pageable);
    Optional<Actor> findByName(String name);
}
