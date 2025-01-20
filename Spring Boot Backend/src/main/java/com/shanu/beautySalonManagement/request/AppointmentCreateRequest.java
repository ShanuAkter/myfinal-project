package com.shanu.beautySalonManagement.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentCreateRequest {
    private LocalDateTime appointmentDate;
    private Long serviceId;
    private Long userId;
    private String promoCode; // Add promo code field
    private Long expertId; // Add expert id field
}