package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.PromoCodeDTO;
import com.application.masl7tak.model.PromoCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface PromoCodeAPI {
    @GetMapping("public/promo_code")
    public ResponseEntity <Object> findAll();
    @GetMapping("public/promo_code/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id);

    @PostMapping("admin/promo_code")
    public ResponseEntity<Object> save(@RequestBody PromoCode promo_code);
    @GetMapping("user/expired")
    public ResponseEntity<Object> expired(@RequestParam (value = "code") String code,@RequestParam (value = "business_id") Long business_id);

    @PutMapping("admin/promo_code/{id}")
    public ResponseEntity<Object> update(@RequestBody PromoCode promo_code, @PathVariable Long id);

    @DeleteMapping("admin/promo_code/{id}")
    public void deleteById(@PathVariable Long id);
}
