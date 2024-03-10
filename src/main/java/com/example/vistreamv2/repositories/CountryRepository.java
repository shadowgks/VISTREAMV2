package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Country;
import com.example.vistreamv2.models.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findCountriesByIso(String iso);

}
