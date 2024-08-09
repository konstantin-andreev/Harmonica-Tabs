package org.harmonicatabs.controller;

import org.harmonicatabs.model.entity.Harmonica;
import org.harmonicatabs.service.HarmonicaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {
    private final HarmonicaService harmonicaService;

    public InfoController(HarmonicaService harmonicaService) {
        this.harmonicaService = harmonicaService;
    }

    @GetMapping("/harmonica/{id}")
    public String viewHarmonica(@PathVariable long id, Model model){
        if(!this.harmonicaService.getHarmonica(id).getLeft()) return "redirect:/user/user_panel";
        Harmonica harmonica = this.harmonicaService.getHarmonica(id).getRight();
        model.addAttribute("viewHarmonica", harmonica);
        return "harmonica";
    }

    @GetMapping("/song")
    public String viewSong(){
        return "song";
    }

    @GetMapping("/user")
    public String viewUser(){
        return "user";
    }
}
