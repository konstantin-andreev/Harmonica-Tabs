package org.harmonicatabs.controller;

import jakarta.validation.Valid;
import org.harmonicatabs.config.UserSession;
import org.harmonicatabs.model.dtos.UserLoginDTO;
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

    @ModelAttribute("loginUser")
    public UserLoginDTO newUserLoginDTO() {return new UserLoginDTO();}

    private final UserService userService;
    private final UserSession userSession;

    public UserController(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }


    @GetMapping("/register")
    public String viewRegister(){
        if(userSession.isLoggedIn()) return "redirect:/home";
        return "register";
    }

    @GetMapping("/login")
    public String viewLogin() {
        if(userSession.isLoggedIn()) return "redirect:/home";
        return "login";}

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerUser", userRegisterDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUser",
                    bindingResult);
            return "redirect:/register";
        }
        boolean success = this.userService.register(userRegisterDTO);
        if(!success){
            redirectAttributes.addFlashAttribute("registerUser", userRegisterDTO);
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginUser", userLoginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUser",
                    bindingResult);
            return "redirect:/login";
        }
        boolean success = this.userService.login(userLoginDTO);
        if(!success){
            redirectAttributes.addFlashAttribute("loginUser", userLoginDTO);
            redirectAttributes.addFlashAttribute("incorrectCredentials", true);
            return "redirect:/login";
        }
        return "redirect:/home";
    }
}
