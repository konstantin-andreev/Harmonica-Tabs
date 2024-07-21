package org.harmonicatabs.service.impl;

import org.harmonicatabs.model.entity.Country;
import org.harmonicatabs.model.enums.Continent;
import org.harmonicatabs.repository.CountryRepository;
import org.harmonicatabs.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Country addCountry(String name, Continent continent) {
        Optional<Country> optional = this.countryRepository.findByName(name);
        if (optional.isPresent()) return optional.get();
        Country country = new Country();
        country.setName(name);
        country.setContinent(continent);
        this.countryRepository.saveAndFlush(country);
        return country;
    }
}
