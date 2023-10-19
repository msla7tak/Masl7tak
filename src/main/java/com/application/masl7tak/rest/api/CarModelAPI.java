package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.model.CarBrand;
import com.application.masl7tak.model.CarModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface CarModelAPI {
    @GetMapping("public/car_model")
    public ResponseEntity <List<CarModelDTO>> findAll();
    @GetMapping("public/car_model/{id}")
    public ResponseEntity<CarModelDTO> findById(@PathVariable Long id);

    @PostMapping("admin/car_model")
    public ResponseEntity<CarModel> save(@RequestBody CarModel carModel);

    @PutMapping("admin/car_model/{id}")
    public ResponseEntity<CarModel> update(@RequestBody CarModel carModel, @PathVariable Long id);

    @DeleteMapping("admin/car_model/{id}")
    public void deleteById(@PathVariable Long id);
}
