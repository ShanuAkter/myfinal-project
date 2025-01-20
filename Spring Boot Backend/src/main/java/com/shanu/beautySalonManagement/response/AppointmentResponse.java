package com.shanu.beautySalonManagement.response;

import com.shanu.beautySalonManagement.enums.Appointment_Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AppointmentResponse {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime appointmentDate;
    private LocalDateTime suggestedAppointmentDate;
    private double vatPrice;
    private double discountPrice;
    private double totalPrice;
    private double totalServicePrice; // Add this field
    private Appointment_Status status;
    private List<Long> serviceIds;
    private Long userId;
    private Long expertIds; // Add this field

}