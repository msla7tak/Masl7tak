package com.application.masl7tak.service;

import com.application.masl7tak.dto.CarBrandDTO;
import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.model.CarBrand;
import com.application.masl7tak.model.CarModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarModelService {


    public ResponseEntity<List<CarModelDTO>> findAll();

    public ResponseEntity<CarModelDTO> findById(Long id);

    public ResponseEntity<CarModel> save(CarModel carModel);

    public void deleteById(Long id);

}
