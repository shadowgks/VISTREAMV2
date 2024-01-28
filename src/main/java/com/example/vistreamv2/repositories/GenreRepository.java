package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
