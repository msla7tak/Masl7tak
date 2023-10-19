package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.model.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface CityAPI {
    @GetMapping("public/city")
    public ResponseEntity <List<City>> findAll();
    @GetMapping("public/city/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id);

    @PostMapping("admin/city")
    public ResponseEntity<City> save(@RequestBody City city);

    @PutMapping("admin/city/{id}")
    public ResponseEntity<City> update(@RequestBody City city, @PathVariable Long id);

    @DeleteMapping("admin/city/{id}")
    public void deleteById(@PathVariable Long id);
}
