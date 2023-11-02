package com.application.masl7tak.rest.controller;

import com.application.masl7tak.model.PromoCode;
import com.application.masl7tak.rest.api.PromoCodeAPI;
import com.application.masl7tak.service.PromoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PromoCodeController implements PromoCodeAPI {
    @Autowired
    private   PromoCodeService promoCodeService;


    @Override
    public ResponseEntity<List<PromoCode>> findAll() {
        return promoCodeService.findAll( );
    }

    @Override
    public ResponseEntity<PromoCode> findById(@PathVariable Long id) {
        return promoCodeService.findById(id);
    }

    @Override
    public ResponseEntity<PromoCode>  save(@RequestBody PromoCode promo_code) {
        return promoCodeService.save(promo_code);
    }

    @Override
    public ResponseEntity<PromoCode> update(@RequestBody PromoCode promo_code, @PathVariable Long id) {
        promo_code.setId(id);
        return promoCodeService.save(promo_code);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        promoCodeService.deleteById(id);
    }
}
