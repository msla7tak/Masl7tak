package com.application.masl7tak.service;

import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Faq;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FaqService {


    public ResponseEntity<List<Faq>> findAll();

    public ResponseEntity<Faq> findById(Long id);

    public ResponseEntity<Faq> save(Faq faq);

    public void deleteById(Long id);

    ResponseEntity<Faq> support(String name, String email, String questionEn, int status);

    ResponseEntity<List<Faq>> findAllSupport();
}
