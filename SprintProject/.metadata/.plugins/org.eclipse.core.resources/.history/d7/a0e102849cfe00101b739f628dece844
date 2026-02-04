package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cg.entity.User;
import com.cg.service.UserService;

@Controller
public class AuthController {
	@Autowired
    private UserService userService;
 
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
 
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
 
    @PostMapping("/register")
    public String registerUser(User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
}
