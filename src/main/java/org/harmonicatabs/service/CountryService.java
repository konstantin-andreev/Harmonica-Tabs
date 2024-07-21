package org.harmonicatabs.service;

import org.harmonicatabs.model.entity.Country;
import org.harmonicatabs.model.enums.Continent;
import org.springframework.stereotype.Service;


public interface CountryService {

    Country addCountry(String name, Continent continent);
}
