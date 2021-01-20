package com.chgkportal.controller;

import com.chgkportal.entity.User;
import com.chgkportal.repository.UserRepository;
import com.chgkportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('adminpanel:crud')")
public class AdminPanelController {

    private final UserService userService;

    @Autowired
    public AdminPanelController(UserService userService) {
        this.userService = userService;
    }

    // TODO
    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "administration/user_list";
    }


    @GetMapping("*/userUpdateForm")
    public String showFormToUpdateUser(@RequestParam("userEmail") String email, Model model) {
        User user = userService.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist")
        );
        model.addAttribute("user", user);
        return "administration/user_form";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        user.getUserProfile().setEmail(user.getEmail());
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/userAddForm")
    public String showFormToAddUser(Model model) {
        model.addAttribute("newUser", new User());
        return "redirect:/registration/";
    }
}
