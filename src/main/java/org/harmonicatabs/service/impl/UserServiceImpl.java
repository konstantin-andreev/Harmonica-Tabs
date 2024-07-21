package org.harmonicatabs.service.impl;

import org.harmonicatabs.config.UserSession;
import org.harmonicatabs.model.dtos.UserLoginDTO;
import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.harmonicatabs.model.entity.Country;
import org.harmonicatabs.model.entity.User;
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
    private final UserSession userSession;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, CountryService countryService, UserSession userSession, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.countryService = countryService;
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        Optional<User> optional = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if(optional.isPresent()) return false;
        Country country = this.countryService.addCountry(userRegisterDTO.getCountryName(), userRegisterDTO.getContinent());
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        user.setCountry(country);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        if(userRegisterDTO.getUsername().equals("konstantin.pl")) user.setAdmin(true);
        this.userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> optional = this.userRepository.findByUsername(userLoginDTO.getUsername());
        if(optional.isEmpty()) return false;
        User user = optional.get();
        if(!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) return false;
        this.userSession.login(user);
        return true;
    }


}
