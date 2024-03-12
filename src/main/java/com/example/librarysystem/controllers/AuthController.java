package com.example.librarysystem.controllers;

import com.example.librarysystem.dto.user.UserCreateRequest;
import com.example.librarysystem.dto.user.UserCreateResponse;
import com.example.librarysystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public String create(@ModelAttribute("user") UserCreateRequest request) {
        UserCreateResponse response = userService.saveUser(request);
        return "redirect:/library/all";
    }

    @GetMapping("/signup")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserCreateRequest());
        return "signup";
    }
}
