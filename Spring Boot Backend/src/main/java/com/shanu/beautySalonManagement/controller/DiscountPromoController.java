package com.shanu.beautySalonManagement.controller;

import com.shanu.beautySalonManagement.model.DiscountPromo;
import com.shanu.beautySalonManagement.service.DiscountPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@CrossOrigin(origins = "*")
public class DiscountPromoController {

    @Autowired
    private DiscountPromoService discountPromoService;

    @GetMapping("/find/{promoCode}")
    public ResponseEntity<DiscountPromo> getDiscountByPromoCode(@PathVariable String promoCode) {
        DiscountPromo discountPromo = discountPromoService.findByPromoCode(promoCode);
        if (discountPromo != null) {
            return ResponseEntity.ok(discountPromo);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiscountPromo>> getAllDiscountPromos() {
        List<DiscountPromo> discountPromos = discountPromoService.findAll();
        return ResponseEntity.ok(discountPromos);
    }

    @PostMapping("/create")
    public ResponseEntity<DiscountPromo> createDiscountPromo(@RequestBody DiscountPromo discountPromo) {
        DiscountPromo createdDiscountPromo = discountPromoService.save(discountPromo);
        return ResponseEntity.ok(createdDiscountPromo);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DiscountPromo> updateDiscountPromo(@PathVariable Long id, @RequestBody DiscountPromo discountPromo) {
        DiscountPromo updatedDiscountPromo = discountPromoService.update(id, discountPromo);
        if (updatedDiscountPromo != null) {
            return ResponseEntity.ok(updatedDiscountPromo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDiscountPromo(@PathVariable Long id) {
        discountPromoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}