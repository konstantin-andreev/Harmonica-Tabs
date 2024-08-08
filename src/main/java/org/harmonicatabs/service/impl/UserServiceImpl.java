package org.harmonicatabs.service.impl;

import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.harmonicatabs.model.entity.Country;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.repository.UserRepository;
import org.harmonicatabs.service.CountryService;
import org.harmonicatabs.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CountryService countryService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, CountryService countryService, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.countryService = countryService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> optional = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if(optional.isPresent()) return false;
        Country country = this.countryService.addCountry(userRegisterDTO.getCountryName(), userRegisterDTO.getContinent());
        UserEntity user = this.modelMapper.map(userRegisterDTO, UserEntity.class);
        user.setCountry(country);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        this.userRepository.saveAndFlush(user);
        return true;
    }


}
