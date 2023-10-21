package com.application.masl7tak.service;

import com.application.masl7tak.dto.ReadmeDTO;
import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.model.Readme;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReadmeService {


    public ResponseEntity<List<ReadmeDTO>> findAll();

    public ResponseEntity<ReadmeDTO>   findById(Long readmeId);

    public ResponseEntity<ReadmeDTO> save(Readme readme);
    public List<ServicesDTO>   getMaxCouponUsage();
    public void deleteById(Long id);

    ResponseEntity<String> update(String comment, Float rate, Long readmeId);
    ResponseEntity<Object> updateInvoicePath( String path, String total_invoice, Long readmeId);

    ResponseEntity<Object> findUserCoupons(Long userId);

    ResponseEntity<Object> findBusinessCoupons(Long businessId,String date);

    ResponseEntity<Object> coupons_date(String scheduleDate, String scheduleTime, int confirmDate, Long readmeId);

    ResponseEntity<ReadmeDTO> coupons_invoice(int confirmInvoice,String reason, Long readmeId);

    List<ServicesDTO> most_redeemed(Long business_id);

    List<ServicesDTO> most_visited(Long businessId);
}
