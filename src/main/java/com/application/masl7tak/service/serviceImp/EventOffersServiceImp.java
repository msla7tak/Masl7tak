package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.EventOffersRepository;
import com.application.masl7tak.dto.EventOffersDTO;
import com.application.masl7tak.model.EventOffers;
import com.application.masl7tak.service.EventOffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EventOffersServiceImp implements EventOffersService {

    private final EventOffersRepository eventOffersRepository;
    @Autowired
    public EventOffersServiceImp(EventOffersRepository eventOffersRepository) {
        this.eventOffersRepository = eventOffersRepository;
    }

    @Override
    public ResponseEntity<List<EventOffersDTO>> findAll() {
        try {

            return new ResponseEntity<>(eventOffersRepository.getAll_EventOffers(),HttpStatus.OK);

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<EventOffersDTO> findById(Long id) {
        try {
            return new ResponseEntity<>(  eventOffersRepository.findBy_Id(id),HttpStatus.OK);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return new ResponseEntity<>( new EventOffersDTO(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<EventOffers> save(EventOffers eventOffers) {
        try {
            return new ResponseEntity<>(  eventOffersRepository.save(eventOffers),HttpStatus.OK);
    }
        catch (Exception exception)
    {
        exception.printStackTrace();
    }
        return new ResponseEntity<>( new EventOffers(), HttpStatus.INTERNAL_SERVER_ERROR);

}
    @Override
    public void deleteById(Long id) {
        eventOffersRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<EventOffersDTO>> findAllBusinessEvents() {
        try {

            return new ResponseEntity<>(eventOffersRepository.get_business_events(),HttpStatus.OK);

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);    }
}
