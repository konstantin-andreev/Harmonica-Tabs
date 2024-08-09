package org.harmonicatabs.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    public HomeController() {
    }

    @GetMapping("/home")
    public String viewHome(){

        return "home";
    }
    @GetMapping("/")
    public String index(){

        return "index";
    }


}
