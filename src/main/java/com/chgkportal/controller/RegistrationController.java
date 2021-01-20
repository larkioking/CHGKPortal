package com.chgkportal.controller;

import com.chgkportal.entity.User;
import com.chgkportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showSignUpForm(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/createUser")
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.register(user);
        return "redirect:/";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(Model model, @PathVariable String code) {
        userService.activateUser(code);
        /*
        model.addAttribute("completedRegistrationMes", "Ваш профиль был подтверждён. \n" +
                "Через несколько секунд вы перейдёте на главную страницу и сможете войти в аккаунт");
         */
        return "successful_confirmation";
    }

}
