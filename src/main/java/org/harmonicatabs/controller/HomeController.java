package org.harmonicatabs.controller;

import org.harmonicatabs.config.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserSession userSession;

    public HomeController(UserSession userSession) {
        this.userSession = userSession;
    }

    @GetMapping("/home")
    public String viewHome(){
        if(!this.userSession.isLoggedIn()) return "redirect:/";
        return "home";
    }
    @GetMapping("/")
    public String index(){
        if(this.userSession.isLoggedIn()) return "redirect:/home";
        return "index";
    }

    @GetMapping("/logout")
    public String logout(){
        this.userSession.logout();
        return "redirect:/";
    }

    @GetMapping("/user_panel")
    public String showUserPanel(){
        if(!this.userSession.isLoggedIn()) return "redirect:/";
        return "user_panel";
    }
}
