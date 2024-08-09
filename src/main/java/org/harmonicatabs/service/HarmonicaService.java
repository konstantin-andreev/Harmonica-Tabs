package org.harmonicatabs.service;

import org.harmonicatabs.model.dtos.AddHarmonicaDTO;
import org.harmonicatabs.model.entity.Harmonica;
import org.modelmapper.internal.Pair;


public interface HarmonicaService {
    boolean add(AddHarmonicaDTO harmonica);

    void deleteHarmonicaWithId(long id);

    Pair<Boolean, Harmonica> getHarmonica(long id);
}
