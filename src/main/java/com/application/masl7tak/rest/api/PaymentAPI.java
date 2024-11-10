package com.application.masl7tak.rest.api;

import com.application.masl7tak.model.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/")
public interface PaymentAPI {
    @GetMapping("admin/payment")
    public ResponseEntity <Object> findAll();
    @GetMapping("user/payment/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id);

    @PostMapping("user/payment")
    public ResponseEntity<Object> save(@RequestBody Payment payment);

    @PutMapping("user/payment/{id}")
    public ResponseEntity<Object> update(@RequestBody Payment payment, @PathVariable Long id);

    @DeleteMapping("admin/payment/{id}")
    public void deleteById(@PathVariable Long id);


    @GetMapping("user/my_payment")
    public ResponseEntity<Object> findUserCoupons(@RequestParam(name = "userId") Long userId);

    @GetMapping("business/payment")
    public ResponseEntity<Object> findBusinessCoupons(@RequestParam(name = "business_id") Long business_id,@RequestParam(name = "schedule_date", required = false) String date);

}
