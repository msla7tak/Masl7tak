package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.PromoCodeDTO;
import com.application.masl7tak.model.PromoCode;
import com.application.masl7tak.rest.api.PromoCodeAPI;
import com.application.masl7tak.service.PromoCodeService;
import lombok.extern.java.Log;
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
    public ResponseEntity<Object> findAll() {
        return promoCodeService.findAll( );
    }

    @Override
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return promoCodeService.findById(id);
    }

    @Override
    public ResponseEntity<Object>  save(@RequestBody PromoCode promo_code) {
        log.info("Tetst: "+promo_code+ " ");
        return promoCodeService.save(promo_code);
    }

    @Override
    public ResponseEntity<Object> expired(String code,Long business_id) {
        return promoCodeService.expired(code,business_id);    }

    @Override
    public ResponseEntity<Object> update(@RequestBody PromoCode promo_code, @PathVariable Long id) {
        promo_code.setId(id);
        return promoCodeService.save(promo_code);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        promoCodeService.deleteById(id);
    }
}
