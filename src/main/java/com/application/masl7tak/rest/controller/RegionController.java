package com.application.masl7tak.rest.controller;


import com.application.masl7tak.dto.RegionDTO;
import com.application.masl7tak.model.Region;
import com.application.masl7tak.rest.api.RegionAPI;
import com.application.masl7tak.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    public class RegionController implements RegionAPI {
      private  final RegionService  regionService;
    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }


    @Override
    public ResponseEntity<List<RegionDTO>> findAll() {
        return regionService.findAll();
    }

    @Override
    public ResponseEntity<RegionDTO> findById(@PathVariable Long id) {
        return regionService.findById(id);
    }

    @Override
    public ResponseEntity<Region>  save(@RequestBody Region region) {
        return regionService.save(region);
    }

    @Override
    public ResponseEntity<Region> update(@RequestBody Region region, @PathVariable Long id) {
        region.setId(id);
        return regionService.save(region);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        regionService.deleteById(id);
    }
}
