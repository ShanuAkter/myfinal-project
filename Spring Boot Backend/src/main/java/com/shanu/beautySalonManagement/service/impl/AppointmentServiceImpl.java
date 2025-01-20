package com.shanu.beautySalonManagement.service.impl;

import com.shanu.beautySalonManagement.enums.Appointment_Status;
import com.shanu.beautySalonManagement.model.*;
import com.shanu.beautySalonManagement.repository.AppointmentRepository;
import com.shanu.beautySalonManagement.repository.ExpertRepository;
import com.shanu.beautySalonManagement.repository.OurServicesRepository;
import com.shanu.beautySalonManagement.repository.UserRepository;
import com.shanu.beautySalonManagement.request.AppointmentCreateRequest;
import com.shanu.beautySalonManagement.response.AppointmentResponse;
import com.shanu.beautySalonManagement.service.AppointmentService;
import com.shanu.beautySalonManagement.service.DiscountPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private OurServicesRepository ourServicesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscountPromoService discountPromoService;
    @Autowired
    private ExpertRepository expertRepository;

    @Override
    public AppointmentResponse createAppointment(AppointmentCreateRequest request) {
        Appointment appointment = new Appointment();
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setSuggestedAppointmentDate(null); // Set to null at creation time

        Optional<OurServices> serviceOptional = ourServicesRepository.findById(request.getServiceId());
        if (serviceOptional.isPresent()) {
            OurServices service = serviceOptional.get();
            double servicePrice = service.getPrice();

            // Calculate VAT price as 10% of the service price
            double vatPrice = servicePrice * 0.10;
            appointment.setVatPrice(vatPrice);

            // Fetch discount from DiscountPromo table
            double discountPrice = 0.0;
            if (request.getPromoCode() != null) {
                DiscountPromo discountPromo = discountPromoService.findByPromoCode(request.getPromoCode());
                if (discountPromo != null && discountPromo.isActive()) {
                    discountPrice = servicePrice * (discountPromo.getDiscountPercentage() / 100);
                }
            }
            appointment.setDiscountPrice(discountPrice);

            // Calculate total price
            double totalPrice = servicePrice + vatPrice - discountPrice;
            appointment.setTotalPrice(totalPrice);

            appointment.setOurServices(service);
        } else {
            throw new IllegalArgumentException("Service not found");
        }

        // Set expert
        Optional<Expert> expertOptional = expertRepository.findById(request.getExpertId());
        if (expertOptional.isPresent()) {
            appointment.setExpert(expertOptional.get());
        } else {
            throw new IllegalArgumentException("Expert not found");
        }

        // Set user
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if (userOptional.isPresent()) {
            appointment.setUser(userOptional.get());
        } else {
            throw new IllegalArgumentException("User not found");
        }

        appointment.setStatus(Appointment_Status.PENDING);

        appointment = appointmentRepository.save(appointment);
        return mapToResponse(appointment);
    }


    @Override
    public AppointmentResponse getAppointment(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        return appointmentOptional.map(this::mapToResponse).orElse(null);
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentResponse updateAppointmentStatus(Long id, String status) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            appointment.setStatus(Appointment_Status.valueOf(status));
            appointment = appointmentRepository.save(appointment);
            return mapToResponse(appointment);
        }
        return null;
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByUserId(Long userId) {
        List<Appointment> appointments = appointmentRepository.findByUserId(userId);
        return appointments.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByServiceId(Long serviceId) {
        List<Appointment> appointments = appointmentRepository.findByOurServicesId(serviceId);
        return appointments.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse updateAppointment(Long id, Appointment request) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            appointment.setAppointmentDate(request.getAppointmentDate());
            appointment.setSuggestedAppointmentDate(request.getSuggestedAppointmentDate());
            appointment.setVatPrice(request.getVatPrice());
            appointment.setDiscountPrice(request.getDiscountPrice());
            appointment.setTotalPrice(request.getTotalPrice());
            appointment.setStatus(request.getStatus());
            appointment = appointmentRepository.save(appointment);
            return mapToResponse(appointment);
        }
        return null;
    }

    private AppointmentResponse mapToResponse(Appointment appointment) {
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());
        response.setCreatedAt(appointment.getCreatedAt());
//        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setSuggestedAppointmentDate(appointment.getSuggestedAppointmentDate());
//        response.setVatPrice(appointment.getVatPrice());
//        response.setDiscountPrice(appointment.getDiscountPrice());
//        response.setTotalPrice(appointment.getTotalPrice());

//        if (appointment.getOurServices() != null) {
//            response.setTotalServicePrice(appointment.getOurServices().getPrice());
//            response.setServiceIds(List.of(appointment.getOurServices().getId()));
//        } else {
//            response.setTotalServicePrice(0);
//            response.setServiceIds(List.of());
//        }

        response.setStatus(appointment.getStatus());

        if (appointment.getUser() != null) {
            response.setUserId(appointment.getUser().getId());
        } else {
            response.setUserId(null);
        }

        if (appointment.getExpert() != null) {
            response.setExpertIds(appointment.getExpert().getId());
        } else {
            response.setExpertIds(null);
        }

        return response;
    }
}