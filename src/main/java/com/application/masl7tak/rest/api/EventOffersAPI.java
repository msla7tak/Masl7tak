package com.application.masl7tak.rest.api;

import com.application.masl7tak.model.EventOffers;
import com.application.masl7tak.dto.EventOffersDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface EventOffersAPI {


    @GetMapping("public/event-offers")
    public ResponseEntity<List<EventOffersDTO>>findAll();
    @GetMapping("public/event-offers/business-events")
    public ResponseEntity<List<EventOffersDTO>>findAllBusinessEvents();

    @GetMapping("public/event-offers/{id}")
    public ResponseEntity <EventOffersDTO> findById(@PathVariable Long id);

    @PostMapping("admin/event-offers")
    public ResponseEntity <EventOffers> save(@RequestBody EventOffers eventOffers);

    @PutMapping("admin/event-offers/{id}")
    public ResponseEntity <EventOffers> update(@RequestBody EventOffers eventOffers, @PathVariable Long id);

    @DeleteMapping("admin/event-offers/{id}")
    public void deleteById(@PathVariable Long id);
}