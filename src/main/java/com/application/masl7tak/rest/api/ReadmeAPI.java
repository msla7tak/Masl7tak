package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.ReadmeDTO;
import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.model.Readme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface ReadmeAPI {
    @GetMapping("user/readme_services")
    public ResponseEntity <List<ReadmeDTO>> findAll();
    @GetMapping("public/readme_service/most_requested")
    public List<ServicesDTO>getMaxCouponUsage();
    @GetMapping("business/most_redeemed")
    public List<ServicesDTO>most_redeemed(@RequestParam(name = "business_id") Long business_id);
    @GetMapping("business/most_visited")
    public List<ServicesDTO>most_visited(@RequestParam(name = "business_id") Long business_id);

    @GetMapping("user/readme_service")
    public ResponseEntity<ReadmeDTO>   findById(@RequestParam(name = "readmeId") Long readmeId);

    @GetMapping("user/my_coupons")
    public ResponseEntity<Object> findUserCoupons(@RequestParam(name = "userId") Long userId);

    @GetMapping("business/coupons")
    public ResponseEntity<Object> findBusinessCoupons(@RequestParam(name = "business_id") Long business_id,@RequestParam(name = "schedule_date", required = false) String date);

    @PostMapping("user/readme_service")
    public ResponseEntity<ReadmeDTO> save(@RequestBody Readme readme);

    @PutMapping("user/readme_service")
    public ResponseEntity<String> update(@RequestParam(name = "comment") String comment,
                                         @RequestParam(name = "rate") Float rate,
                                         @RequestParam(name = "readmeId" ) Long readmeId);
    @PutMapping("user/coupons_date")
    public ResponseEntity<Object> coupons_date(
            @RequestParam(name = "schedule_date") String schedule_date,
                                         @RequestParam(name = "schedule_time") String schedule_time,
                                         @RequestParam(name = "confirmation_state") int confirm_date,
                                         @RequestParam(name = "readmeId" ) Long readmeId);
    @PutMapping("business/coupons_invoice")
    public ResponseEntity<ReadmeDTO> coupons_invoice(
                                         @RequestParam(name = "confirm_invoice") int confirm_date,
                                         @RequestParam(name = "readmeId" ) Long readmeId);
    @PutMapping("user/readme_invoice")
    public ResponseEntity<Object> updateInvoicePath(@RequestParam(name = "path") String path,
                                                    @RequestParam(name = "total_invoice") String total_invoice,
                                                    @RequestParam(name = "readmeId" ) Long readmeId);


    @DeleteMapping("user/readme_service/{id}")
    public void deleteById(@PathVariable Long id);
}
