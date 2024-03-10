package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.models.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
    Optional<Production> findProductionByIdTmdb(Long id);

}
