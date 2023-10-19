package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.CarBrandDTO;
import com.application.masl7tak.model.CarBrand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface CarBrandAPI {
    @GetMapping("public/car_brand")
    public ResponseEntity <List<CarBrandDTO>> findAll();
    @GetMapping("public/car_brand/{id}")
    public ResponseEntity<CarBrandDTO> findById(@PathVariable Long id);

    @PostMapping("admin/car_brand")
    public ResponseEntity<CarBrand> save(@RequestBody CarBrand carBrand);

    @PutMapping("admin/car_brand/{id}")
    public ResponseEntity<CarBrand> update(@RequestBody CarBrand CarBrand, @PathVariable Long id);

    @DeleteMapping("admin/car_brand/{id}")
    public void deleteById(@PathVariable Long id);
}
