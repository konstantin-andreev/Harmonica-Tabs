package org.harmonicatabs.controller;

import org.harmonicatabs.service.HarmonicaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    private final HarmonicaService harmonicaService;

    public DeleteController(HarmonicaService harmonicaService) {
        this.harmonicaService = harmonicaService;
    }

    @GetMapping("/harmonica/{id}")
    public String deleteHarmonica(@PathVariable long id){

        this.harmonicaService.deleteHarmonicaWithId(id);


        return "redirect:/user/user_panel";

    }
}
