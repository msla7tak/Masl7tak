package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.InsuranceDTO;
import com.application.masl7tak.model.Insurance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface InsuranceAPI {
    @GetMapping("admin/insurances")
    public ResponseEntity <List<InsuranceDTO>> findAll();

    @GetMapping("public/insurance")
    public ResponseEntity<InsuranceDTO> findById(@RequestParam("id") Long id);

    @PostMapping("user/insurances")
    public ResponseEntity<InsuranceDTO> save(@RequestBody Insurance insurance);

    @PutMapping("user/insurances/{id}")
    public ResponseEntity<InsuranceDTO> update(@RequestBody Insurance insurance, @PathVariable Long id);
    @PutMapping("user/update_invoice")
    public ResponseEntity<InsuranceDTO> updateInvoice(@RequestParam String invoice_id, @RequestParam Long id);
    @PutMapping("/admin/insurance/accept_offer")
    public ResponseEntity<Object> AcceptOffer(@RequestParam(name = "insurance_logo") String insurance_logo,
                                         @RequestParam(name = "insurance_contact") String insurance_contact,
                                         @RequestParam(name = "insurance_type") String insurance_type,
                                         @RequestParam(name = "insurance_price") String insurance_price,
                                         @RequestParam(name = "insurance_period") String insurance_period,
                                         @RequestParam(name = "commission") String commission,
                                         @RequestParam(name = "insurance_price_ar") String insurance_price_ar,
                                         @RequestParam(name = "insuranceId" ) Long insuranceId);
    @DeleteMapping("admin/insurance/{id}")
    public void deleteById(@PathVariable Long id);
}
