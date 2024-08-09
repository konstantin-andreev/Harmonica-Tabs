package org.harmonicatabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/harmonica")
    public String viewHarmonica(){
        return "user_panel";
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
