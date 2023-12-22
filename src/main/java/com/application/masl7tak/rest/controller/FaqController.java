package com.application.masl7tak.rest.controller;


import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Faq;
import com.application.masl7tak.rest.api.FaqAPI;
import com.application.masl7tak.service.FaqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    public class FaqController implements FaqAPI {
      private  final FaqService regionService;

    public FaqController(FaqService regionService) {
        this.regionService = regionService;
    }

    @Override
    public ResponseEntity<Object> findAll( String lang) {
        return regionService.findAll(lang);
    }

    @Override
    public ResponseEntity<Object> findAllSupport() {
        return regionService.findAllSupport();
    }

    @Override
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return regionService.findById(id);
    }

    @Override
    public ResponseEntity<Object>  save(@RequestBody Faq faq) {
        return regionService.save(faq);
    }



    @Override
    public ResponseEntity<Object> support(String name, String email, String question_en, int status) {
        return regionService.support(name,email,question_en,status);
    }

    @Override
    public ResponseEntity<Object> update(@RequestBody Faq faq, @PathVariable Long id) {
        faq.setId(id);
        return regionService.update(faq);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        regionService.deleteById(id);
    }
}
