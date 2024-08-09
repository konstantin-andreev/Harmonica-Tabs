package org.harmonicatabs.controller;

import org.harmonicatabs.model.entity.Song;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.service.SongService;
import org.harmonicatabs.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final SongService songService;

    public HomeController(UserService userService, SongService songService) {
        this.userService = userService;
        this.songService = songService;
    }

    @GetMapping("/home")
    public String viewHome(Model model){
        List<UserEntity> users = this.userService.getAllUsers();
        List<Song> songs = this.songService.getAllSongs();
        model.addAttribute("users", users);
        model.addAttribute("songs", songs);
        return "home";
    }
    @GetMapping("/")
    public String index(){

        return "index";
    }


}
