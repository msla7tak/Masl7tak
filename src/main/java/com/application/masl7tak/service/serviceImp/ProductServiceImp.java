package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.Repository.ProductRepository;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Products;
import com.application.masl7tak.service.ProductService;
import com.application.masl7tak.utils.Utils;
import com.application.masl7tak.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<ProductDTO> findAll() {
        return productRepository.getAllProduct();
    }

    @Override
    public Products findById(Long id) {
        return productRepository.findBy_Id(id);
    }

    @Override
    public ResponseEntity<SuccessDTO> save(Products product) {
        try {
            Products savedProduct = productRepository.save(product);
            SuccessDTO successDTO = new SuccessDTO(savedProduct.getId(), Constants.DATA_Inserted);
            log.info("Product: " + successDTO.toString());
            return ResponseEntity.ok(successDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new SuccessDTO(0L, Constants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
