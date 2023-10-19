package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.ProductService;
import com.application.masl7tak.model.Services;
import com.application.masl7tak.model.filter.ServicesFilter;
import com.application.masl7tak.rest.api.ServicesAPI;
import com.application.masl7tak.service.ServicesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j

@RestController
public class ServicesController implements ServicesAPI {
    private final ServicesService servicesService;
    @Autowired
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @Override
    public ResponseEntity<List<ServicesDTO>> findAll(ServicesFilter criteria) {

        log.info("test : "+ criteria);

          return servicesService.findServicesByCriteria(criteria);


    }

    @Override
    public ResponseEntity<ServicesDTO> findById(@PathVariable Long id) {
        return servicesService.findById(id);
    }

    @Override
    public ResponseEntity<Services> save(@RequestBody Services services) {
        return servicesService.save(services);
    }

    @Override
    public ResponseEntity<Object> setProductService(ProductService productService, MultipartFile[] files) {
        log.info("test    "+productService);
        return servicesService.setProductService(productService, files);    }

    @Override
    public ResponseEntity<Object> setService_event(ProductService productService, MultipartFile[] files) {
        log.info("test    "+productService);
        return servicesService.setService_event(productService, files);
    }

//    @Override
//    public ResponseEntity<Services> saveService_with_image(Services services, MultipartFile[] files) {
//        return servicesService.save(services,  files);
//    }

    @Override
    public ResponseEntity<Object> update(ProductService services, MultipartFile[] files) {
        return servicesService.UpdateProductService(services,files);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        servicesService.deleteById(id);
    }
}
