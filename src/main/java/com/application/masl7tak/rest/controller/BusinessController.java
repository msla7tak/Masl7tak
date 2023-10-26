package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.dto.BusinessDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Business;
import com.application.masl7tak.model.BusinessBranch;
import com.application.masl7tak.model.filter.BusinessFilter;
import com.application.masl7tak.rest.api.BusinessAPI;
import com.application.masl7tak.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BusinessController implements BusinessAPI {
    private  final BusinessService businessService;
    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }


    @Override
    public ResponseEntity<List<BusinessDTO>> findAll(BusinessFilter criteria) {
        return businessService.findBusinessByCriteria(criteria);

    }

    @Override
    public ResponseEntity<List<BusinessDTO>> getAll() {
        return businessService.getAll();
    }

    @Override
    public ResponseEntity<Object> findMostVisited() {
        return businessService.findMostVisited();
    }

    @Override
    public ResponseEntity<BusinessDTO> findById(Long id) {
        return businessService.findById(id);
    }

    @Override
    public ResponseEntity<String> findBusinessTermsConditions(Long id) {
        return businessService.findBusinessTermsConditions(id) ;
    }

    @Override
    public ResponseEntity<String> active(String id) {
        long longId = Long.parseLong(id);

        return businessService.active(longId) ;
    }

    @Override
    public ResponseEntity<Object> save(BusinessBranch business) {
        return businessService.save(business);
    }

    @Override
    public ResponseEntity<Object> create(Map<String, String> business, Long userId) {
        return businessService.create(business,userId);
    }

    @Override
    public ResponseEntity<Object> update(BusinessBranch business, Long id) {
        business.setId(id);
        return businessService.update(business);
    }

    @Override
    public ResponseEntity<AnalyticsDTO> findAnalyticsById(Long id) {
        return businessService.findAnalyticsById(id) ;
    }



    @Override
    public void deleteById(Long id) {
        businessService.deleteById(id);

    }
}
