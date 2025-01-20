package com.shanu.beautySalonManagement.service.impl;

import com.shanu.beautySalonManagement.model.User;
import com.shanu.beautySalonManagement.repository.UserRepository;
import com.shanu.beautySalonManagement.request.LoginRequest;
import com.shanu.beautySalonManagement.response.AuthResponse;
import com.shanu.beautySalonManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && loginRequest.getPassword().equals(user.getPassword())) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setId(user.getId().toString());
            authResponse.setName(user.getName());
            authResponse.setEmail(user.getEmail());
            authResponse.setRole(user.getRole().name());
            return authResponse;
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            // Ensure the appointment list is initialized
            if (existingUser.getAppointment() == null) {
                existingUser.setAppointment(new ArrayList<>());
            }
            existingUser.getAppointment().clear();
            if (user.getAppointment() != null) {
                existingUser.getAppointment().addAll(user.getAppointment());
            }
            return userRepository.save(existingUser);
        }).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteExpertsByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}