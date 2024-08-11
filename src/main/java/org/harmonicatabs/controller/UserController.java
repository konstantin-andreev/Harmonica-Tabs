package org.harmonicatabs.controller;

import jakarta.validation.Valid;
import org.harmonicatabs.model.dtos.UserLoginDTO;
import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.harmonicatabs.service.UserService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
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
            return "redirect:/user/register";
        }
        boolean success = this.userService.register(userRegisterDTO);
        if(!success){
            redirectAttributes.addFlashAttribute("registerUser", userRegisterDTO);
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:/user/register";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "redirect:/";
    }

    @GetMapping("/user_panel")
    public String showUserPanel(Model model){
        return "user_panel";
    }

    @GetMapping("/messages")
    public String showMessages(){
        return "messages";
    }

    @GetMapping("/remove/song/{id}")
    public String removeFavSong(@PathVariable long id){
        this.userService.removeFavSong(id);
        return "redirect:/user/user_panel";
    }
}
