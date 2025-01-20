package com.shanu.beautySalonManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shanu.beautySalonManagement.enums.User_Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;


    @Enumerated(EnumType.STRING)
    private User_Role role = User_Role.CUSTOMER;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointment;




}
