package com.application.masl7tak.service;

import com.application.masl7tak.model.City;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {


    public ResponseEntity<List<City>> findAll();

    public ResponseEntity<City> findById(Long id);

    public ResponseEntity<City> save(City city);

    public void deleteById(Long id);

}
