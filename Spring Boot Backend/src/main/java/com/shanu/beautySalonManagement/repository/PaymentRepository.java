package com.shanu.beautySalonManagement.repository;

import com.shanu.beautySalonManagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
