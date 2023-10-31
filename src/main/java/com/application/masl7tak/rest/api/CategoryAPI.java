package com.application.masl7tak.rest.api;


import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequestMapping( path = "/api/")
public interface CategoryAPI {
    @GetMapping("public/category")
    public ResponseEntity<List<CategoryDTO> > findAll(@RequestParam(value = "lang", required = false) String lang);

    @GetMapping("public/category/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id);

    @PostMapping("/admin/category")
    public ResponseEntity<Object>  save(@RequestBody Category category);

    @PutMapping("admin/category/{id}")
    public ResponseEntity<Object>  update(@RequestBody Category category, @PathVariable Long id);

    @DeleteMapping("admin/category/{id}")
    public void deleteById(@PathVariable Long id);


}
