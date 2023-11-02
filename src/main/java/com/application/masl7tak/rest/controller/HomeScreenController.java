package com.application.masl7tak.rest.controller;

import com.application.masl7tak.model.filter.BusinessFilter;
import com.application.masl7tak.rest.api.HomeScreenAPI;
import com.application.masl7tak.dto.EventOffersDTO;
import com.application.masl7tak.service.BusinessService;
import com.application.masl7tak.service.EventOffersService;
import com.application.masl7tak.dto.BusinessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeScreenController implements HomeScreenAPI {
    private final BusinessService businessService;


    private final EventOffersService productEventOffersService;
    @Autowired
    public HomeScreenController(BusinessService businessService, EventOffersService productEventOffersService) {
        this.businessService = businessService;
        this.productEventOffersService = productEventOffersService;
    }

    @Override
    public ResponseEntity<List<BusinessDTO>>  findBusinessByCriteria(BusinessFilter criteria) {

            return businessService.findBusinessByTopRated(criteria);


    }

    @Override
    public ResponseEntity< List<EventOffersDTO>> findAllOffers() {
        return productEventOffersService.findAll();
    }
}
