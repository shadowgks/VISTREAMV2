package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("SELECT m FROM Media m " +
            "WHERE m.director LIKE CONCAT('%', :searchTerm, '%') " +
            "OR LOWER(m.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Optional<Page<Media>> findMediaByContaining(@Param("searchTerm") String searchTerm, Pageable pageable);
}
