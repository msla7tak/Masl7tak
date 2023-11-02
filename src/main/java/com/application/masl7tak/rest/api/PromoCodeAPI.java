package com.application.masl7tak.rest.api;

import com.application.masl7tak.model.PromoCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface PromoCodeAPI {
    @GetMapping("public/promo_code")
    public ResponseEntity <List<PromoCode>> findAll();
    @GetMapping("public/promo_code/{id}")
    public ResponseEntity<PromoCode> findById(@PathVariable Long id);

    @PostMapping("admin/promo_code")
    public ResponseEntity<PromoCode> save(@RequestBody PromoCode promo_code);

    @PutMapping("admin/promo_code/{id}")
    public ResponseEntity<PromoCode> update(@RequestBody PromoCode promo_code, @PathVariable Long id);

    @DeleteMapping("admin/promo_code/{id}")
    public void deleteById(@PathVariable Long id);
}
