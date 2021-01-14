package com.chgkportal.controller;

import com.chgkportal.entity.User;
import com.chgkportal.model.Role;
import com.chgkportal.model.Status;
import com.chgkportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @PostMapping("/createUser")
    public String addUser(@RequestParam("newUser") User user) {
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);
        return "main_page";
    }
}
