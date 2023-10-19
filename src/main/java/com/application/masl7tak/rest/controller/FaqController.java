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
    public ResponseEntity<List<Faq>> findAll() {
        return regionService.findAll();
    }

    @Override
    public ResponseEntity<List<Faq>> findAllSupport() {
        return regionService.findAllSupport();
    }

    @Override
    public ResponseEntity<Faq> findById(@PathVariable Long id) {
        return regionService.findById(id);
    }

    @Override
    public ResponseEntity<Faq>  save(@RequestBody Faq faq) {
        return regionService.save(faq);
    }

    @Override
    public ResponseEntity<Faq> support(String name, String email, String question_en, int status) {
        return regionService.support(name,email,question_en,status);
    }

    @Override
    public ResponseEntity<Faq> update(@RequestBody Faq faq, @PathVariable Long id) {
        faq.setId(id);
        return regionService.save(faq);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        regionService.deleteById(id);
    }
}
