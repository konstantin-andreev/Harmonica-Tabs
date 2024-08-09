package org.harmonicatabs.service;

import org.harmonicatabs.model.dtos.AddHarmonicaDTO;
import org.harmonicatabs.model.entity.Harmonica;


public interface HarmonicaService {
    Harmonica add(AddHarmonicaDTO harmonica);
}
