package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Country;

public interface CountryService {
    Country findCountryByName(String name);
}
