package com.shanu.beautySalonManagement.service;

import com.shanu.beautySalonManagement.model.Appointment;
import com.shanu.beautySalonManagement.request.AppointmentCreateRequest;
import com.shanu.beautySalonManagement.response.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    public AppointmentResponse createAppointment(AppointmentCreateRequest request);
    public AppointmentResponse getAppointment(Long id);
    public List<AppointmentResponse> getAllAppointments();
    public void deleteAppointment(Long id);
    // update appointment status
    public AppointmentResponse updateAppointmentStatus(Long id, String status);
    // get appointments by user id
    public List<AppointmentResponse> getAppointmentsByUserId(Long userId);
    // get appointments by service id
    public List<AppointmentResponse> getAppointmentsByServiceId(Long serviceId);

    public AppointmentResponse updateAppointment(Long id, Appointment request);
}