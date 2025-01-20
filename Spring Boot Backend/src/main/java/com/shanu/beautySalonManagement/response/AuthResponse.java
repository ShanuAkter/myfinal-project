package com.shanu.beautySalonManagement.response;


import lombok.Data;

@Data
public class AuthResponse {
    private String id;
    private String name;
    private String email;
    private String role;
}
