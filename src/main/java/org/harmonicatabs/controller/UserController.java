package org.harmonicatabs.controller;

import jakarta.validation.Valid;
import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.harmonicatabs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @ModelAttribute("registerUser")
    public UserRegisterDTO newUserRegisterDTO(){
        return new UserRegisterDTO();
    }
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String viewRegister(){
        return "register";
    }

    @GetMapping("/login")
    public String viewLogin() {return "login";}

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !this.userService.register(userRegisterDTO)){
            redirectAttributes.addFlashAttribute("registerUser", userRegisterDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUser",
                    bindingResult);
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
