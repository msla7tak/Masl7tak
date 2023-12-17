package com.application.masl7tak.service;

import com.application.masl7tak.model.Point;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PointService {


    public ResponseEntity<List<Point>> findAll();

    public ResponseEntity<Point> findById(Long id);

    public ResponseEntity<Object> save(Point point);

    public void deleteById(Long id);
    ResponseEntity<Object> update(Point point);
    ResponseEntity<List<Point>> findRequestsByUserId(Long userId);
}
