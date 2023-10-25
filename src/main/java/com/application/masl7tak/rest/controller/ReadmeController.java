package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.ReadmeDTO;
import com.application.masl7tak.model.Readme;
import com.application.masl7tak.rest.api.ReadmeAPI;
import com.application.masl7tak.service.ReadmeService;
import com.application.masl7tak.dto.ServicesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ReadmeController implements ReadmeAPI {
    private final ReadmeService readmeService;
    @Autowired
    public ReadmeController(ReadmeService readmeService) {
        this.readmeService = readmeService;
    }

    @Override
    public ResponseEntity<List<ReadmeDTO>> findAll() {
        return readmeService.findAll();
    }


    @Override
    public ResponseEntity<ReadmeDTO>  save(@RequestBody Readme readme) {
//        log.info("test: "+ readme);
        readme.setStateName("Active");
        return this.readmeService.save(readme);
    }

    @Override
    public ResponseEntity<String> update(String comment, Float rate, Long readmeId) {

        return this.readmeService.update( comment,  rate,  readmeId);
    }

    @Override
    public ResponseEntity<Object> coupons_date(String schedule_date, String schedule_time, int confirm_date, Long readmeId) {
        return this.readmeService.coupons_date( schedule_date,  schedule_time, confirm_date, readmeId);
    }

    @Override
    public ResponseEntity<ReadmeDTO> coupons_invoice(int confirm_invoice,String reason, Long readmeId) {
        return this.readmeService.coupons_invoice( confirm_invoice,reason, readmeId);
    }

    @Override
    public ResponseEntity<Object> updateInvoicePath(String path,String total_invoice, Long readmeId) {
        return readmeService.updateInvoicePath(path,total_invoice,readmeId);
    }


    @Override
    public List<ServicesDTO> getMaxCouponUsage() {

        return readmeService.getMaxCouponUsage();
    }

    @Override
    public List<ServicesDTO> most_redeemed(Long business_id) {
        return readmeService.most_redeemed(business_id);
    }

    @Override
    public List<ServicesDTO> most_visited(Long business_id) {
        return readmeService.most_visited(business_id);
    }

    @Override
    public ResponseEntity<ReadmeDTO> findById(Long readmeId) {
        return readmeService.findById(readmeId);

    }

    @Override
    public ResponseEntity<Object> findReadmeById(Long readmeId) {
        return readmeService.findReadmeById(readmeId);    }

    @Override
    public ResponseEntity<Object> findUserCoupons(Long userId) {
        return readmeService.findUserCoupons(userId);
    }

    @Override
    public ResponseEntity<Object> findBusinessCoupons(Long business_id, String date) {
        return readmeService.findBusinessCoupons(business_id,date);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        readmeService.deleteById(id);
    }
}
