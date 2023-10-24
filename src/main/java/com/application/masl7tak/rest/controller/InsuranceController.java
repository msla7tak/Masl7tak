package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.InsuranceDTO;
import com.application.masl7tak.model.Insurance;
import com.application.masl7tak.rest.api.InsuranceAPI;
import com.application.masl7tak.service.InsuranceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class InsuranceController implements InsuranceAPI {
    private final InsuranceService insuranceService;
    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @Override
    public ResponseEntity<List<InsuranceDTO>> findAll() {
        return insuranceService.findAll();
    }

    @Override
    public ResponseEntity<InsuranceDTO> findById(@PathVariable Long id) {
        return insuranceService.findById(id);
    }

    @Override
    public ResponseEntity<InsuranceDTO>  save(@RequestBody Insurance insurance) {
        return insuranceService.save(insurance);
    }

    @Override
    public ResponseEntity<InsuranceDTO> update(@RequestBody Insurance insurance, @PathVariable Long id) {
        insurance.setId(id);
        return insuranceService.save(insurance);
    }

    @Override
    public ResponseEntity<Object> AcceptOffer(String insurance_logo, String insurance_contact, String insurance_type,
                                              String insurance_price, String insurance_period, String commission,
                                              String insurance_price_ar, Long insuranceId) {
        return insuranceService.AcceptOffer( insurance_logo,  insurance_contact,  insurance_type,
                insurance_price,  insurance_period,  commission,insurance_price_ar,  insuranceId);


    }

    @Override
    public void deleteById(@PathVariable Long id) {
        insuranceService.deleteById(id);
    }
}
