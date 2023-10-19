package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.model.Admin;
import com.application.masl7tak.rest.api.AdminAPI;
import com.application.masl7tak.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AdminController implements AdminAPI {
    @Autowired
    private  AdminService adminService;


    @Override
    public ResponseEntity<List<Admin>> findAll() {
        return adminService.findAll();
    }

    @Override
    public ResponseEntity<Admin> findById(@PathVariable Long id) {
        return adminService.findById(id);
    }

    @Override
    public ResponseEntity<Admin>  save(@RequestBody Admin admin) {
        return adminService.save(admin);
    }

    @Override
    public ResponseEntity<Admin> update(@RequestBody Admin admin, @PathVariable Long id) {
        admin.setId(id);
        return adminService.save(admin);
    }
    @Override
    public ResponseEntity<AnalyticsDTO> findAnalytics() {
        return adminService.findAnalytics() ;   }
    @Override
    public void deleteById(@PathVariable Long id) {
        adminService.deleteById(id);
    }
}
