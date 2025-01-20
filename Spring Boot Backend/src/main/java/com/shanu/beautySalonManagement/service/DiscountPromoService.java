package com.shanu.beautySalonManagement.service;

import com.shanu.beautySalonManagement.model.DiscountPromo;

import java.util.List;

public interface DiscountPromoService {
    DiscountPromo findByPromoCode(String promoCode);
    List<DiscountPromo> findAll();
    DiscountPromo save(DiscountPromo discountPromo);
    DiscountPromo update(Long id, DiscountPromo discountPromo);
    void delete(Long id);
}