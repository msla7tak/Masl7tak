package com.application.masl7tak.service;

import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.ProductService;
import com.application.masl7tak.model.Services;
import com.application.masl7tak.model.filter.ServicesFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServicesService {


    public ResponseEntity<List<ServicesDTO>> findAll_with_offset(int offset);
    public ResponseEntity<List<ServicesDTO>> findAll();

    public ResponseEntity<ServicesDTO> findById(Long id);

    public ResponseEntity<Services> save(Services services);

    public void deleteById(Long id);

    public ResponseEntity<List<ServicesDTO>> findServicesByCriteria(ServicesFilter criteria);


    ResponseEntity<Services> save(Services services, MultipartFile[] files);

    ResponseEntity<Object> setProductService(ProductService productService, MultipartFile[] files);
    ResponseEntity<Object> UpdateProductService(ProductService productService, MultipartFile[] files);

    ResponseEntity<Object> setService_event(ProductService productService, MultipartFile[] files);

    ResponseEntity<String> active(long longId);

    ResponseEntity<List<ServicesDTO>> findAllAdmin(ServicesFilter criteria);
}
