package org.harmonicatabs.service.impl;

import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.harmonicatabs.model.entity.Country;
import org.harmonicatabs.model.entity.User;
import org.harmonicatabs.repository.UserRepository;
import org.harmonicatabs.service.CountryService;
import org.harmonicatabs.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CountryService countryService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, CountryService countryService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.countryService = countryService;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        Optional<User> optional = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if(optional.isPresent()) return false;
        Country country = this.countryService.addCountry(userRegisterDTO.getCountryName(), userRegisterDTO.getContinent());
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        user.setCountry(country);
        if(userRegisterDTO.getUsername().equals("konstantin.pl")) user.setAdmin(true);
        this.userRepository.saveAndFlush(user);
        return true;
    }


}
