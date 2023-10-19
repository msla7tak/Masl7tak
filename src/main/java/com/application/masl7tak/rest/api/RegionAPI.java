package com.application.masl7tak.rest.api;


import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.dto.RegionDTO;
import com.application.masl7tak.model.Category;
import com.application.masl7tak.model.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping( path = "/api/")
public interface RegionAPI {
    @GetMapping("public/region")
    public ResponseEntity<List<RegionDTO>> findAll();

    @GetMapping("public/region/{id}")
    public ResponseEntity<RegionDTO> findById(@PathVariable Long id);

    @PostMapping("admin/region")
    public ResponseEntity<Region>  save(@RequestBody Region region);

    @PutMapping("admin/region/{id}")
    public ResponseEntity<Region>  update(@RequestBody Region region, @PathVariable Long id);

    @DeleteMapping("admin/region/{id}")
    public void deleteById(@PathVariable Long id);


}
