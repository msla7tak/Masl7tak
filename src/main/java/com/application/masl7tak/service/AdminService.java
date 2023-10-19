package com.application.masl7tak.service;

import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.model.Admin;
import com.application.masl7tak.model.CarModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {


    public ResponseEntity<List<Admin>> findAll();

    public ResponseEntity<Admin> findById(Long id);

    public ResponseEntity<Admin> save(Admin admin);

    public void deleteById(Long id);
    ResponseEntity<AnalyticsDTO> findAnalytics();
}
