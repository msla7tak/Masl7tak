package com.application.masl7tak.service;

import com.application.masl7tak.dto.CarBrandDTO;
import com.application.masl7tak.model.CarBrand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarBrandService {


    public ResponseEntity<List<CarBrandDTO>> findAll();

    public ResponseEntity<CarBrandDTO> findById(Long id);

    public ResponseEntity<CarBrand> save(CarBrand carBrand);

    public void deleteById(Long id);

}
