package com.shanu.beautySalonManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Payment for appointment or Products
    private String paymentType;
    private String paymentMethod;
    private String transactionId;

    // make relationship with appointment and product
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private String serviceName;
    private String customerName;
    private double serviceTotal;
    private String date;
    private String time;
    private double paid;
    private double due;
}