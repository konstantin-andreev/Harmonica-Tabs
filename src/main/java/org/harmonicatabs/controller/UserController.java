package org.harmonicatabs.controller;

import jakarta.validation.Valid;
import org.harmonicatabs.model.dtos.UserLoginDTO;
import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.harmonicatabs.service.UserService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String viewRegister(){
        return "register";
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginUser", new UserLoginDTO());
        return modelAndView;
    }

    @GetMapping("/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("incorrectCredentials", true);
        modelAndView.addObject("loginUser", new UserLoginDTO());
        return modelAndView;
    }

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

}
