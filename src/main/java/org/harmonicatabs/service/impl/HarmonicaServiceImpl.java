package org.harmonicatabs.service.impl;

import org.harmonicatabs.model.dtos.AddHarmonicaDTO;
import org.harmonicatabs.model.entity.Harmonica;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.repository.HarmonicaRepository;
import org.harmonicatabs.repository.UserRepository;
import org.harmonicatabs.service.HarmonicaService;
import org.harmonicatabs.service.session.UserHelperService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HarmonicaServiceImpl implements HarmonicaService {

    private final HarmonicaRepository harmonicaRepository;
    private final UserRepository userRepository;
    private final UserHelperService userHelperService;

    private final ModelMapper modelMapper;

    public HarmonicaServiceImpl(HarmonicaRepository harmonicaRepository, UserRepository userRepository, UserHelperService userHelperService, ModelMapper modelMapper) {
        this.harmonicaRepository = harmonicaRepository;
        this.userRepository = userRepository;
        this.userHelperService = userHelperService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(AddHarmonicaDTO addHarmonicaDTO) {

        Harmonica harmonica = this.modelMapper.map(addHarmonicaDTO, Harmonica.class);
        UserEntity owner = this.userHelperService.getUser();
        harmonica.setOwner(owner);
        owner.getHarmonicas().add(harmonica);
        this.harmonicaRepository.saveAndFlush(harmonica);
        return true;
    }

    @Override
    public void deleteHarmonicaWithId(long id) {
        Optional<Harmonica> harmonica = this.harmonicaRepository.findById(id);
        if(harmonica.isEmpty()) return;
        UserEntity owner = harmonica.get().getOwner();

        if(this.userHelperService.getUser().getUsername().equals(owner.getUsername())){
            owner.getHarmonicas().remove(harmonica.get());
            this.harmonicaRepository.delete(harmonica.get());
        }

    }

    @Override
    public Pair<Boolean, Harmonica> getHarmonica(long id) {
        Optional<Harmonica> harmonica = this.harmonicaRepository.findById(id);
        return harmonica.map(value -> Pair.of(true, value)).orElseGet(() -> Pair.of(false, null));
    }
}
