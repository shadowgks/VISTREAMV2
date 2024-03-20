package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.Country;
import com.example.vistreamv2.repositories.CountryRepository;
import com.example.vistreamv2.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    @Override
    public Country findCountryByName(String name) {
        return countryRepository.findCountryByName(name);
    }

    @Override
    public List<Country> findAllCountry() {
        return countryRepository.findAll();
    }
}
