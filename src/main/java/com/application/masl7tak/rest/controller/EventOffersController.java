package com.application.masl7tak.rest.controller;

import com.application.masl7tak.model.EventOffers;
import com.application.masl7tak.rest.api.EventOffersAPI;
import com.application.masl7tak.service.EventOffersService;
import com.application.masl7tak.dto.EventOffersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventOffersController implements EventOffersAPI {

    private final EventOffersService productEventOffersService;
    @Autowired
    public EventOffersController(EventOffersService productEventOffersService) {
        this.productEventOffersService = productEventOffersService;
    }

    @Override
    public ResponseEntity< List<EventOffersDTO>> findAll() {
        return productEventOffersService.findAll();
    }

    @Override
    public ResponseEntity<List<EventOffersDTO>> findAllBusinessEvents() {
        return productEventOffersService.findAllBusinessEvents();
    }

    @Override
    public ResponseEntity<List<EventOffersDTO>> findAllServicesEvents() {
        return productEventOffersService.findAllServicesEvents();
    }

    @Override
    public ResponseEntity <EventOffersDTO> findById(@PathVariable Long id) {
        return productEventOffersService.findById(id);
    }

    @Override
    public ResponseEntity <EventOffers> save(@RequestBody EventOffers eventOffers) {
        return productEventOffersService.save(eventOffers);
    }

    @Override
    public ResponseEntity <EventOffers> update(@RequestBody EventOffers eventOffers, @PathVariable Long id) {
        eventOffers.setId(id);
        return productEventOffersService.save(eventOffers);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        productEventOffersService.deleteById(id);

    }

}
