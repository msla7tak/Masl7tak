package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.model.CarModel;
import com.application.masl7tak.rest.api.CarModelAPI;
import com.application.masl7tak.service.CarModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CarModelController implements CarModelAPI {
    private  final CarModelService carModelService;
    @Autowired
    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @Override
    public ResponseEntity<List<CarModelDTO>> findAll() {
        return carModelService.findAll();
    }

    @Override
    public ResponseEntity<CarModelDTO> findById(@PathVariable Long id) {
        return carModelService.findById(id);
    }

    @Override
    public ResponseEntity<CarModel>  save(@RequestBody CarModel carBrand) {
        return carModelService.save(carBrand);
    }

    @Override
    public ResponseEntity<CarModel> update(@RequestBody CarModel carBrand, @PathVariable Long id) {
        carBrand.setId(id);
        return carModelService.save(carBrand);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        carModelService.deleteById(id);
    }
}
