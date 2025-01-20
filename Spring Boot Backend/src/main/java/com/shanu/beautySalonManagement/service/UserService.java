package com.shanu.beautySalonManagement.service;

import com.shanu.beautySalonManagement.model.User;
import com.shanu.beautySalonManagement.request.LoginRequest;
import com.shanu.beautySalonManagement.response.AuthResponse;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    AuthResponse loginUser(LoginRequest loginRequest);
    User findByEmail(String email);
    User findById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> findAll();
}