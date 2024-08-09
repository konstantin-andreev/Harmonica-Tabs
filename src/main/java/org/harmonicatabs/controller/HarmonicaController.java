package org.harmonicatabs.controller;

import org.harmonicatabs.service.HarmonicaService;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class HarmonicaController {

    private final HarmonicaService harmonicaService;

    public HarmonicaController(HarmonicaService harmonicaService) {
        this.harmonicaService = harmonicaService;
    }

}
