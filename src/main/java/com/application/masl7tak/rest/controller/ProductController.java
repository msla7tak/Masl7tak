package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Products;
import com.application.masl7tak.rest.api.ProductAPI;
import com.application.masl7tak.service.ProductService;
import com.application.masl7tak.dto.ProductDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductAPI {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Override
    public ResponseEntity<SuccessDTO> addProduct(Products product) {
        return productService.save(product);
    }
    @Override
    public ResponseEntity<Products> getProductById(Long id)  {
        return ResponseEntity.ok(productService.findById(id));
    }

    @Override
    public ResponseEntity<SuccessDTO> updateProduct(Long id, Products updatedProduct) {
        Products existingProduct = productService.findById(id);
        BeanUtils.copyProperties(updatedProduct, existingProduct, "id");
        return productService.save(existingProduct);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
