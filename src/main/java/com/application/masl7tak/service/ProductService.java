package com.application.masl7tak.service;


import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Products;
import com.application.masl7tak.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {


    public List<ProductDTO> findAll();

    public Products findById(Long id);
    public ResponseEntity<SuccessDTO> save(Products product) ;
    public void deleteById(Long id) ;

    // ... other service methods
}
