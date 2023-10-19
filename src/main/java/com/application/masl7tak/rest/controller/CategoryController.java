package com.application.masl7tak.rest.controller;


import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.model.Category;

import com.application.masl7tak.rest.api.CategoryAPI;
import com.application.masl7tak.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    public class CategoryController implements CategoryAPI {
      private final  CategoryService categoryService;
    @Autowired

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return categoryService.findAll();
    }

    @Override
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @Override
    public ResponseEntity<Object>  save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @Override
    public ResponseEntity<Object> update(@RequestBody Category category, @PathVariable Long id) {
        category.setId(id);
        return categoryService.UpdateCategory(category);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
