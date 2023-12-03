package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.*;
import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.model.Admin;
import com.application.masl7tak.model.Point;
import com.application.masl7tak.service.AdminService;
import com.application.masl7tak.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PointServiceImp implements PointService {
    @Autowired
    private  PointRepository pointRepository;
    @Autowired
    private  BusinessRepository businessRepository;

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  ServicesRepository servicesRepository;

    @Override
    public ResponseEntity<List<Point>> findAll() {
        try {

            return new ResponseEntity<>(pointRepository.findAll(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Point> findById(Long id) {
        try {

            return new ResponseEntity<>(pointRepository.findById(id).get(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new Point(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Point> save(Point point) {
        try {
            return new ResponseEntity<>(pointRepository.save(point), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Point(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        pointRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<Point>> findRequestsByUserId(Long userId) {
        try {
            return new   ResponseEntity<List<Point>>( pointRepository.findRequestsByUserId(userId), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


}
