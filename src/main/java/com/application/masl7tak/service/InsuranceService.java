package com.application.masl7tak.service;

import com.application.masl7tak.dto.InsuranceDTO;
import com.application.masl7tak.model.Insurance;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InsuranceService {


    public ResponseEntity<List<InsuranceDTO>> findAll();

    public ResponseEntity<InsuranceDTO> findById(Long id);

    public ResponseEntity<InsuranceDTO> save(Insurance insurance);

    public void deleteById(Long id);

    ResponseEntity<String> AcceptOffer(String insuranceLogo, String insuranceContact, String insuranceType,
                                       String insurancePrice, String insurancePeriod, String commission,
                                       String insurancePriceAr, Long insuranceId);

}
