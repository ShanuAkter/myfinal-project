package com.shanu.beautySalonManagement.controller;

import com.shanu.beautySalonManagement.model.User;
import com.shanu.beautySalonManagement.request.LoginRequest;
import com.shanu.beautySalonManagement.response.AuthResponse;
import com.shanu.beautySalonManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }
}