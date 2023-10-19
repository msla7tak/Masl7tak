package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.CarBrandDTO;
import com.application.masl7tak.model.CarBrand;
import com.application.masl7tak.rest.api.CarBrandAPI;
import com.application.masl7tak.service.CarBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CarBrandController implements CarBrandAPI {
    private final CarBrandService carBrandService;
    @Autowired
    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @Override
    public ResponseEntity<List<CarBrandDTO>> findAll() {
        return carBrandService.findAll();
    }

    @Override
    public ResponseEntity<CarBrandDTO> findById(@PathVariable Long id) {
        return carBrandService.findById(id);
    }

    @Override
    public ResponseEntity<CarBrand>  save(@RequestBody CarBrand carBrand) {
        return carBrandService.save(carBrand);
    }

    @Override
    public ResponseEntity<CarBrand> update(@RequestBody CarBrand carBrand, @PathVariable Long id) {
        carBrand.setId(id);
        return carBrandService.save(carBrand);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        carBrandService.deleteById(id);
    }
}
