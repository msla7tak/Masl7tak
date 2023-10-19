package com.application.masl7tak.service;

import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {


    public ResponseEntity<List<CategoryDTO>> findAll();

    public ResponseEntity<Object> findById(Long id);

    public ResponseEntity<Object> save(Category category);

    public void deleteById(Long id);

    ResponseEntity<Object> UpdateCategory(Category category);
}
