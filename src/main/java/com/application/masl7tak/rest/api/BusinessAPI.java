package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Business;
import com.application.masl7tak.dto.BusinessDTO;
import com.application.masl7tak.model.BusinessBranch;
import com.application.masl7tak.model.filter.BusinessFilter;
import com.application.masl7tak.model.filter.ServicesFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/")
public interface BusinessAPI {
    @GetMapping("public/businesses")
    public ResponseEntity<List<BusinessDTO>> findAll(@ModelAttribute BusinessFilter criteria);
    @GetMapping("admin/businesses")
    public ResponseEntity<List<BusinessDTO>> getAll();

    @GetMapping("public/business")
    public ResponseEntity<BusinessDTO> findById(@RequestParam("id") Long id);

    @GetMapping("public/business/terms_conditions")
    public ResponseEntity<String> findBusinessTermsConditions(@RequestParam("id") Long id);

    @PostMapping("user/businesses")
    public ResponseEntity<Object> save(@RequestBody BusinessBranch business);
    @PostMapping("admin/businesses_user/{userId}")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> business, @PathVariable("userId")  Long userId);

    @PutMapping("business/businesses/{id}")
    public ResponseEntity<Object> update(@RequestBody BusinessBranch business, @PathVariable Long id);

    @GetMapping("business/businesses")
    public ResponseEntity<AnalyticsDTO> findAnalyticsById(@RequestParam("id") Long id);


    @DeleteMapping("business/businesses/{id}")
    public void deleteById(@PathVariable Long id);
}
