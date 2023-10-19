package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.*;
import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.model.Admin;
import com.application.masl7tak.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class AdminServiceImp implements AdminService {
    @Autowired
    private  AdminRepository adminRepository;
    @Autowired
    private  BusinessRepository businessRepository;
    @Autowired
    private  ReadmeRepository readmeRepository;
    @Autowired
    private  InsuranceRepository insuranceRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  ServicesRepository servicesRepository;

    @Override
    public ResponseEntity<List<Admin>> findAll() {
        try {

            return new ResponseEntity<>(adminRepository.findAll(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Admin> findById(Long id) {
        try {

            return new ResponseEntity<>(adminRepository.findById(id).get(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new Admin(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Admin> save(Admin admin) {
        try {
            return new ResponseEntity<>(adminRepository.save(admin), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Admin(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
    @Override
    public ResponseEntity<AnalyticsDTO> findAnalytics() {
        try {

            return new ResponseEntity<>(new AnalyticsDTO(
                    (int) businessRepository.count(),
                    (int) userRepository.count(),
                    (int) servicesRepository.count(),
                    insuranceRepository.count()), HttpStatus.OK);

        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new AnalyticsDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
