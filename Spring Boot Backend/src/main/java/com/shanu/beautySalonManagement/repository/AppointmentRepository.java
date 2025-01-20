package com.shanu.beautySalonManagement.repository;

import com.shanu.beautySalonManagement.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);

    List<Appointment> findByOurServicesId(Long serviceId);
}
