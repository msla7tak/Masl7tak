package com.application.masl7tak.service;

import com.application.masl7tak.model.Payment;
import org.springframework.http.ResponseEntity;

public interface PaymentService {


    public ResponseEntity<Object> findAll();

    public ResponseEntity<Object> findById(Long id);

    public ResponseEntity<Object> save(Payment payment);

    public void deleteById(Long id);

    ResponseEntity<Object> findUserCoupons(Long userId);

    ResponseEntity<Object> findBusinessCoupons(Long businessId, String date);
}

