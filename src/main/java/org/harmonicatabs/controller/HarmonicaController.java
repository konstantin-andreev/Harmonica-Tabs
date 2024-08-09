package org.harmonicatabs.controller;

import org.harmonicatabs.model.dtos.AddHarmonicaDTO;
import org.harmonicatabs.model.entity.Harmonica;
import org.harmonicatabs.service.HarmonicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HarmonicaController {

    private final HarmonicaService harmonicaService;

    public HarmonicaController(HarmonicaService harmonicaService) {
        this.harmonicaService = harmonicaService;
    }

    @PostMapping("/add-harmonica")
    public ResponseEntity<Harmonica> addHarmonica(@RequestBody AddHarmonicaDTO addHarmonicaDTO){
        Harmonica addedHarmonica = this.harmonicaService.add(addHarmonicaDTO);
        return new ResponseEntity<>(addedHarmonica, HttpStatus.CREATED);
    }
}
