package org.harmonicatabs.controller;

import jakarta.validation.Valid;
import org.harmonicatabs.model.dtos.AddHarmonicaDTO;
import org.harmonicatabs.service.HarmonicaService;
import org.harmonicatabs.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/add")
public class AddController {
    private final HarmonicaService harmonicaService;
    private final SongService songService;

    public AddController(HarmonicaService harmonicaService, SongService songService) {
        this.harmonicaService = harmonicaService;
        this.songService = songService;
    }

    @ModelAttribute("addHarmonica")
    public AddHarmonicaDTO newAddHarmonicaDTO(){
        return new AddHarmonicaDTO();
    }

    @GetMapping("/harmonica")
    public String viewAddHarmonica(){
        return "add_harmonica";
    }

    @GetMapping("/song")
    public String viewAddSong(){
        return "add_song";
    }

    @PostMapping("/harmonica")
    public String addHarmonica(@Valid AddHarmonicaDTO addHarmonicaDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addHarmonica", addHarmonicaDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addHarmonica",
                    bindingResult);
            return "redirect:/add/harmonica";
        }

        this.harmonicaService.add(addHarmonicaDTO);
        return "redirect:/user/user_panel";
    }

    @PostMapping("/song")
    public String addSong(){
        return "redirect:/user/user_panel";
    }
}
