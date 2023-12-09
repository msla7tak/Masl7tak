package com.application.masl7tak.service;

import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Faq;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FaqService {


    public ResponseEntity<Object> findAll( String lang);

    public ResponseEntity<Object> findById(Long id);

    public ResponseEntity<Object> save(Faq faq);

    public void deleteById(Long id);

    ResponseEntity<Object> support(String name, String email, String questionEn, int status);

    ResponseEntity<Object> findAllSupport();


}
