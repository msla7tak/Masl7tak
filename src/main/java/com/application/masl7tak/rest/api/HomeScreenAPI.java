package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.BusinessDTO;
import com.application.masl7tak.dto.EventOffersDTO;
import com.application.masl7tak.model.filter.BusinessFilter;
import com.application.masl7tak.model.filter.ServicesFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/public/home_screen")
public interface HomeScreenAPI {
    @GetMapping(path = {"/business"})
    public ResponseEntity <List<BusinessDTO>>  findBusinessByCriteria(BusinessFilter criteria);

    @GetMapping(path = {"/product_offers"})
    public ResponseEntity<List<EventOffersDTO>>findAllOffers();

}
