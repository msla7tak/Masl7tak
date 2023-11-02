package com.application.masl7tak.service;

import com.application.masl7tak.model.PromoCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromoCodeService {


    public ResponseEntity<List<PromoCode>> findAll();

    public ResponseEntity<PromoCode> findById(Long id);

    public ResponseEntity<PromoCode> save(PromoCode city);

    public void deleteById(Long id);

}
