package com.shanu.beautySalonManagement.repository;

import com.shanu.beautySalonManagement.model.DiscountPromo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountPromoRepository extends JpaRepository<DiscountPromo, Long> {
    DiscountPromo findByPromoCode(String promoCode);
}