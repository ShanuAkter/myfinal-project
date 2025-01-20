package com.shanu.beautySalonManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OurServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private double price;

    @Lob
    @Column(length = 100000)
    private byte[] serviceImage;

    @ManyToMany(mappedBy = "ourServices")
    private Set<Expert> experts;

    @JsonIgnore
    @OneToMany(mappedBy = "ourServices")
    private List<Appointment> appointment;
}