package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.model.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface AdminAPI {
    @GetMapping("public/admin")
    public ResponseEntity <List<Admin>> findAll();
    @GetMapping("public/admin/{id}")
    public ResponseEntity<Admin> findById(@PathVariable Long id);

    @PostMapping("admin/admin")
    public ResponseEntity<Admin> save(@RequestBody Admin admin);

    @PutMapping("admin/admin/{id}")
    public ResponseEntity<Admin> update(@RequestBody Admin admin, @PathVariable Long id);

    @DeleteMapping("admin/admin/{id}")
    public void deleteById(@PathVariable Long id);
    @GetMapping("admin/analytics")
    public ResponseEntity<AnalyticsDTO> findAnalytics();
}
