package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Country;

import java.util.List;

public interface CountryService {
    Country findCountryByName(String name);
    List<Country> findAllCountry();
}
