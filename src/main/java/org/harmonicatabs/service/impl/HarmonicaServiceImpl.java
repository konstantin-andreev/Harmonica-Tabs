package org.harmonicatabs.service.impl;

import org.harmonicatabs.model.dtos.AddHarmonicaDTO;
import org.harmonicatabs.model.entity.Harmonica;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.repository.HarmonicaRepository;
import org.harmonicatabs.repository.UserRepository;
import org.harmonicatabs.service.HarmonicaService;
import org.harmonicatabs.service.session.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HarmonicaServiceImpl implements HarmonicaService {

    private final HarmonicaRepository harmonicaRepository;
    private final UserRepository userRepository;
    private final UserHelperService userHelperService;

    public HarmonicaServiceImpl(HarmonicaRepository harmonicaRepository, UserRepository userRepository, UserHelperService userHelperService) {
        this.harmonicaRepository = harmonicaRepository;
        this.userRepository = userRepository;
        this.userHelperService = userHelperService;
    }

    @Override
    public Harmonica add(AddHarmonicaDTO harmonica) {
        return null;
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
}
