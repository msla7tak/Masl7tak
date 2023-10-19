package com.application.masl7tak.service;

import com.application.masl7tak.model.EventOffers;
import com.application.masl7tak.dto.EventOffersDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventOffersService {


    public ResponseEntity < List<EventOffersDTO>>findAll();

    public ResponseEntity<EventOffersDTO> findById(Long id);

    public ResponseEntity <EventOffers> save(EventOffers eventOffers);

    public void deleteById(Long id);

    ResponseEntity<List<EventOffersDTO>> findAllBusinessEvents();
}
