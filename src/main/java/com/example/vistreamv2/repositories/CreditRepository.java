package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    Optional<Credit> findCreditByIdTmdb(Long id);
}
