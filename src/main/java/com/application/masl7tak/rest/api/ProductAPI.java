package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Products;
import com.application.masl7tak.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface ProductAPI {


    @GetMapping("public/product")
    public ResponseEntity<List<ProductDTO>> getAllProducts();
    @PostMapping("business/product")
    public ResponseEntity<SuccessDTO> addProduct(@Validated @RequestBody Products product);


    @GetMapping("public/business/product/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id);

    @PutMapping("business/product/{id}")
    public ResponseEntity<SuccessDTO> updateProduct(@PathVariable Long id, @Validated @RequestBody Products updatedProduct);

    @DeleteMapping("business/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id);
}
