package com.shanu.beautySalonManagement.controller;

import com.shanu.beautySalonManagement.model.Appointment;
import com.shanu.beautySalonManagement.request.AppointmentCreateRequest;
import com.shanu.beautySalonManagement.response.AppointmentResponse;
import com.shanu.beautySalonManagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentCreateRequest request) {
        AppointmentResponse response = appointmentService.createAppointment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable Long id) {
        AppointmentResponse response = appointmentService.getAppointment(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> responses = appointmentService.getAllAppointments();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointmentStatus(@PathVariable Long id, @RequestParam String status) {
        AppointmentResponse response = appointmentService.updateAppointmentStatus(id, status);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updates/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Long id, @RequestBody Appointment request) {
        AppointmentResponse response = appointmentService.updateAppointment(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByUserId(@PathVariable Long userId) {
        List<AppointmentResponse> responses = appointmentService.getAppointmentsByUserId(userId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByServiceId(@PathVariable Long serviceId) {
        List<AppointmentResponse> responses = appointmentService.getAppointmentsByServiceId(serviceId);
        return ResponseEntity.ok(responses);
    }
}