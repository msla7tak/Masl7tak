package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.model.Admin;
import com.application.masl7tak.model.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface PointAPI {
    @GetMapping("admin/point")
    public ResponseEntity<List<Point>> findAll();

    @GetMapping("user/point_request/{id}")
    public ResponseEntity<Point> findById(@PathVariable Long id);
    @GetMapping("user/point_requests")
    public ResponseEntity<List<Point>> findRequestsByUserId(@RequestParam Long user_id);

    @PostMapping("user/point_request")
    public ResponseEntity<Object> save(@RequestBody Point point);

    @PutMapping("admin/point/{id}")
    public ResponseEntity<Object> update(@RequestBody Point point, @PathVariable Long id);

    @DeleteMapping("admin/point/{id}")
    public void deleteById(@PathVariable Long id);
}
