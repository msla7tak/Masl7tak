package com.application.masl7tak.service;

import com.application.masl7tak.model.Point;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PointService {


    public ResponseEntity<List<Point>> findAll();

    public ResponseEntity<Point> findById(Long id);

    public ResponseEntity<Point> save(Point point);

    public void deleteById(Long id);

    ResponseEntity<List<Point>> findRequestsByUserId(Long userId);
}
