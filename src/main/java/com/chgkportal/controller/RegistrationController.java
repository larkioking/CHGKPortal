package com.chgkportal.controller;

import com.chgkportal.entity.User;
import com.chgkportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String addUser(@ModelAttribute("newUser") @Valid User newUser, BindingResult bindingResult) {
        System.out.println(bindingResult.hasErrors());
        System.out.println(bindingResult.getModel());
        System.out.println(newUser);
        System.out.println(newUser.getUserProfile());
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.register(newUser);
        return "successful_registration";
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
