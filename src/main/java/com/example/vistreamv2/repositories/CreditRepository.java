package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.models.entity.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    Optional<Credit> findCreditByIdTmdb(Long id);
    @Query("SELECT m FROM Credit m " +
            "WHERE (LOWER(m.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) ")
    Optional<Page<Credit>> findMediaBySearch(
            @Param("searchTerm") String searchTerm,
            Pageable pageable);
}
