package com.application.masl7tak.service;

import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.dto.RegionDTO;
import com.application.masl7tak.model.Category;
import com.application.masl7tak.model.Region;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegionService {


    public ResponseEntity<List<RegionDTO>> findAll(String lang);

    public ResponseEntity<RegionDTO> findById(Long id);

    public ResponseEntity<Region> save(Region region);

    public void deleteById(Long id);
}
