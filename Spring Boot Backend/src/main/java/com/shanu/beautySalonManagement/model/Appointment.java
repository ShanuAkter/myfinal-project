package com.shanu.beautySalonManagement.model;

import com.shanu.beautySalonManagement.enums.Appointment_Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime appointmentDate;
    private LocalDateTime suggestedAppointmentDate;
    private double vatPrice = 0.1;
    private double discountPrice;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private Appointment_Status status;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private OurServices ourServices;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payment;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;
}