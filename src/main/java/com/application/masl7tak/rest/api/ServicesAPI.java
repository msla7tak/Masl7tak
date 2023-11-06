package com.application.masl7tak.rest.api;


import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.ProductService;
import com.application.masl7tak.model.Services;
import com.application.masl7tak.model.filter.ServicesFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/")
public interface ServicesAPI {


    @GetMapping("public/services")
    public ResponseEntity<List<ServicesDTO>>  findAll(@ModelAttribute ServicesFilter criteria);
    @GetMapping("public/max_amount")
    public ResponseEntity<Object>  findMaxAmount();
    @GetMapping("admin/services")
    public ResponseEntity<List<ServicesDTO>>  findAllAdmin(@ModelAttribute ServicesFilter criteria);

    @GetMapping("public/services/{id}")
    public ResponseEntity<ServicesDTO> findById(@PathVariable Long id);
    @CrossOrigin(origins = "http://127.0.0.1:5500")

    @PostMapping("business/services")
    public ResponseEntity<Services>  save(@RequestBody Services services);
    @PutMapping("admin/services/active/{id}")
    public ResponseEntity<String> active(@PathVariable("id") String id);
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("business/service/save")
    public ResponseEntity<Object>  setProductService(@ModelAttribute ProductService productService,@RequestParam(value = "files", required = false) MultipartFile[] files) ;
    @PostMapping("business/service_event/save")
    public ResponseEntity<Object>  setService_event(@ModelAttribute ProductService productService,@RequestParam(value = "files", required = false) MultipartFile[] files) ;
//    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @PostMapping("business/services/save")
//    public ResponseEntity<Services> saveService_with_image(@ModelAttribute  Services services, @RequestParam(value = "file") MultipartFile[] files);

    @PutMapping("business/service/update")
    public ResponseEntity<Object>  update(@ModelAttribute ProductService productService, @RequestParam(value = "files", required = false) MultipartFile[] files);

    @DeleteMapping("business/services/{id}")
    public void deleteById(@PathVariable Long id);
}