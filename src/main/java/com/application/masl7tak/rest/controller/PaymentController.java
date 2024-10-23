package com.application.masl7tak.rest.controller;

import com.application.masl7tak.model.Payment;
import com.application.masl7tak.rest.api.PaymentAPI;
import com.application.masl7tak.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentController implements PaymentAPI {
    @Autowired
    private PaymentService paymentService;


    @Override
    public ResponseEntity<Object> findAll() {
        return paymentService.findAll( );
    }

    @Override
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @Override
    public ResponseEntity<Object>  save(@RequestBody Payment payment) {
        log.info("Tetst: "+payment+ " ");
        return paymentService.save(payment);
    }



    @Override
    public ResponseEntity<Object> update(@RequestBody Payment payment, @PathVariable Long id) {
        payment.setId(id);
        return paymentService.save(payment);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        paymentService.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> findUserCoupons(Long userId) {
        return paymentService.findUserCoupons( userId);
    }

    @Override
    public ResponseEntity<Object> findBusinessCoupons(Long business_id, String date) {
        return paymentService.findBusinessCoupons( business_id,  date);
    }
}
