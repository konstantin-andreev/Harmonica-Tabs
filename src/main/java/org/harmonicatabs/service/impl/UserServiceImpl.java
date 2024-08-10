package org.harmonicatabs.service.impl;

import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.harmonicatabs.model.entity.Country;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.repository.HarmonicaRepository;
import org.harmonicatabs.repository.SongRepository;
import org.harmonicatabs.repository.UserRepository;
import org.harmonicatabs.service.CountryService;
import org.harmonicatabs.service.UserService;
import org.harmonicatabs.service.session.UserHelperService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CountryService countryService;
    private final PasswordEncoder passwordEncoder;
    private final UserHelperService userHelperService;
    private final SongRepository songRepository;
    private final HarmonicaRepository harmonicaRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, CountryService countryService, PasswordEncoder passwordEncoder, UserHelperService userHelperService, SongRepository songRepository, HarmonicaRepository harmonicaRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.countryService = countryService;
        this.passwordEncoder = passwordEncoder;
        this.userHelperService = userHelperService;
        this.songRepository = songRepository;
        this.harmonicaRepository = harmonicaRepository;
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

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll().stream().filter(user -> !user.getUsername().equals(this.userHelperService.getUser().getUsername())).collect(Collectors.toList());
    }

    @Override
    public Pair<Boolean, UserEntity> getUser(long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        return user.map(value -> Pair.of(true, value)).orElseGet(() -> Pair.of(false, null));
    }

    @Override
    public void deleteUserWithId(long id) {
        Optional<UserEntity> optional = this.userRepository.findById(id);
        if(optional.isEmpty()) return;
        if(!this.userHelperService.hasRole("ADMIN")) return;
        UserEntity user = optional.get();
        this.songRepository.deleteAll(user.getSongs());
        this.harmonicaRepository.deleteAll(user.getHarmonicas());
        this.userRepository.delete(user);

    }


}
