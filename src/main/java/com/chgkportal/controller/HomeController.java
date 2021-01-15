package com.chgkportal.controller;

import com.chgkportal.entity.User;
import com.chgkportal.model.Role;
import com.chgkportal.model.Status;
import com.chgkportal.repository.UserRepository;
import com.chgkportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String loadMainPage() {
        return "main_page";
    }

    @GetMapping("/registration")
    public String showSignUpForm(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration/createUser")
    public String addUser(@ModelAttribute("newUser") User user) {

        userService.register(user);
        return "redirect:/";
    }
}
