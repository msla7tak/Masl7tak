package com.application.masl7tak.service;

import com.application.masl7tak.dto.PromoCodeDTO;
import com.application.masl7tak.model.PromoCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromoCodeService {


    public ResponseEntity<Object> findAll();

    public ResponseEntity<Object> findById(Long id);

    public ResponseEntity<Object> save(PromoCode city);

    public void deleteById(Long id);

    ResponseEntity<Object> expired(String code,Long business_id);
}
