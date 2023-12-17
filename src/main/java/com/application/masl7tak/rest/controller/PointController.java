package com.application.masl7tak.rest.controller;

import com.application.masl7tak.model.Point;
import com.application.masl7tak.rest.api.PointAPI;
import com.application.masl7tak.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PointController implements PointAPI {
    @Autowired
    private  PointService pointService;


    @Override
    public ResponseEntity<List<Point>> findAll() {
        return pointService.findAll();
    }

    @Override
    public ResponseEntity<Point> findById(@PathVariable Long id) {
        return pointService.findById(id);
    }

    @Override
    public ResponseEntity<List<Point>> findRequestsByUserId(Long user_id) {
        return pointService.findRequestsByUserId(user_id);
    }

    @Override
    public ResponseEntity<Object>  save(@RequestBody Point point) {
        return pointService.save(point);
    }

    @Override
    public ResponseEntity<Object> update(@RequestBody Point point, @PathVariable Long id) {
        point.setId(id);
        return pointService.update(point);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        pointService.deleteById(id);
    }
}
