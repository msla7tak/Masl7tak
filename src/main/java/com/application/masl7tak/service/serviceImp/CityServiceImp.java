package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.CityRepository;
import com.application.masl7tak.model.City;
import com.application.masl7tak.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CityServiceImp implements CityService {
    private final CityRepository  cityRepository;
    @Autowired
    public CityServiceImp(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public ResponseEntity<List<City>> findAll() {
        try {

            return new ResponseEntity<List<City>>(cityRepository.findAll(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<City> findById(Long id) {
        try {

            return new ResponseEntity<>(cityRepository.findById(id).orElseThrow(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new City(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<City> save(City City) {
        try {
            return new ResponseEntity<>(cityRepository.save(City), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new City(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }


}
