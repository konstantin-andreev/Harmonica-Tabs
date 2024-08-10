package org.harmonicatabs.controller;

import org.harmonicatabs.service.HarmonicaService;
import org.harmonicatabs.service.SongService;
import org.harmonicatabs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    private final HarmonicaService harmonicaService;
    private final SongService songService;
    private final UserService userService;

    public DeleteController(HarmonicaService harmonicaService, SongService songService, UserService userService) {
        this.harmonicaService = harmonicaService;
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/harmonica/{id}")
    public String deleteHarmonica(@PathVariable long id){
        this.harmonicaService.deleteHarmonicaWithId(id);
        return "redirect:/user/user_panel";

    }

    @GetMapping("/song/{id}")
    public String deleteSong(@PathVariable long id){
        this.songService.deleteSongWithId(id);
        return "redirect:/user/user_panel";
    }

    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable long id){
        this.userService.deleteUserWithId(id);
        return "redirect:/home";
    }

}
