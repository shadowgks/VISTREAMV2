package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
