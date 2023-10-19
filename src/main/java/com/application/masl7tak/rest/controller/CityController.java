package com.application.masl7tak.rest.controller;

import com.application.masl7tak.model.City;
import com.application.masl7tak.rest.api.CityAPI;
import com.application.masl7tak.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CityController implements CityAPI {
    private  final CityService cityService;
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public ResponseEntity<List<City>> findAll() {
        return cityService.findAll();
    }

    @Override
    public ResponseEntity<City> findById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @Override
    public ResponseEntity<City>  save(@RequestBody City city) {
        return cityService.save(city);
    }

    @Override
    public ResponseEntity<City> update(@RequestBody City city, @PathVariable Long id) {
        city.setId(id);
        return cityService.save(city);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
    }
}
