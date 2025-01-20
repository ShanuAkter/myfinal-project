package com.shanu.beautySalonManagement.service.impl;

import com.shanu.beautySalonManagement.model.DiscountPromo;
import com.shanu.beautySalonManagement.repository.DiscountPromoRepository;
import com.shanu.beautySalonManagement.service.DiscountPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountPromoServiceImpl implements DiscountPromoService {

    @Autowired
    private DiscountPromoRepository discountPromoRepository;

    @Override
    public DiscountPromo findByPromoCode(String promoCode) {
        return discountPromoRepository.findByPromoCode(promoCode);
    }

    @Override
    public List<DiscountPromo> findAll() {
        return discountPromoRepository.findAll();
    }

    @Override
    public DiscountPromo save(DiscountPromo discountPromo) {
        return discountPromoRepository.save(discountPromo);
    }

    @Override
    public DiscountPromo update(Long id, DiscountPromo discountPromo) {
        if (discountPromoRepository.existsById(id)) {
            discountPromo.setId(id);
            return discountPromoRepository.save(discountPromo);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        discountPromoRepository.deleteById(id);
    }
}