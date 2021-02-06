package com.chgkportal.controller;

import com.chgkportal.entity.User;
import com.chgkportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Value("${avatar.upload.path}")
    private String uploadPath;

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
    public String addUser(@ModelAttribute("newUser") @Valid User newUser,
                          BindingResult bindingResult,
                          @RequestParam("file") MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "registration";
        }

        if (file != null && !file.getOriginalFilename().isBlank()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            newUser.setProfilePicture(resultFileName);
        }

        userService.register(newUser);
        return "successful_registration";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable String code) {
        userService.activateUser(code);

        return "successful_confirmation";
    }

}
