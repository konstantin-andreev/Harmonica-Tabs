package org.harmonicatabs.controller;

import jakarta.validation.Valid;
import org.harmonicatabs.model.dtos.AddHarmonicaDTO;
import org.harmonicatabs.model.dtos.AddSongDTO;
import org.harmonicatabs.service.HarmonicaService;
import org.harmonicatabs.service.SongService;
import org.harmonicatabs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/add")
public class AddController {
    private final HarmonicaService harmonicaService;
    private final SongService songService;
    private final UserService userService;

    public AddController(HarmonicaService harmonicaService, SongService songService, UserService userService) {
        this.harmonicaService = harmonicaService;
        this.songService = songService;
        this.userService = userService;
    }

    @ModelAttribute("addHarmonica")
    public AddHarmonicaDTO newAddHarmonicaDTO(){
        return new AddHarmonicaDTO();
    }
    @ModelAttribute("addSong")
    public AddSongDTO newAddSongDTO(){
        return new AddSongDTO();
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
    public String addSong(@Valid AddSongDTO addSongDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addHarmonica", addSongDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addSong",
                    bindingResult);
            return "redirect:/add/song";
        }

        this.songService.add(addSongDTO);
        return "redirect:/user/user_panel";
    }

    @GetMapping("/favsong/{id}")
    public String addSongToFavourites(@PathVariable long id){
        this.userService.addSongToFavourites(id);
        return "redirect:/user/user_panel";
    }

}
