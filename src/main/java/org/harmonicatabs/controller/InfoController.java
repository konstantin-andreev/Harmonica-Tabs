package org.harmonicatabs.controller;

import org.harmonicatabs.model.entity.Harmonica;
import org.harmonicatabs.model.entity.Song;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.service.HarmonicaService;
import org.harmonicatabs.service.SongService;
import org.harmonicatabs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {
    private final HarmonicaService harmonicaService;
    private final SongService songService;
    private final UserService userService;

    public InfoController(HarmonicaService harmonicaService, SongService songService, UserService userService) {
        this.harmonicaService = harmonicaService;
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/harmonica/{id}")
    public String viewHarmonica(@PathVariable long id, Model model){
        if(!this.harmonicaService.getHarmonica(id).getLeft()) return "redirect:/user/user_panel";
        Harmonica harmonica = this.harmonicaService.getHarmonica(id).getRight();
        model.addAttribute("viewHarmonica", harmonica);
        return "harmonica";
    }

    @GetMapping("/song/{id}")
    public String viewSong(@PathVariable long id, Model model){
        if(!this.songService.getSong(id).getLeft()) return "redirect:/user/user_panel";
        Song song = this.songService.getSong(id).getRight();
        model.addAttribute("viewSong", song);
        return "song";
    }

    @GetMapping("/user/{id}")
    public String viewUser(@PathVariable long id, Model model){
        if(!this.userService.getUser(id).getLeft()) return "redirect:/home";
        UserEntity user = this.userService.getUser(id).getRight();
        model.addAttribute("viewUser", user);
        return "user";
    }
}
