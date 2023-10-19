package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.CarModelRepository;
import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.model.CarModel;
import com.application.masl7tak.service.CarModelService;
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
public class CarModelServiceImp implements CarModelService {
    private final CarModelRepository  carModelRepository;
    @Autowired
    public CarModelServiceImp(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Override
    public ResponseEntity<List<CarModelDTO>> findAll() {
        try {

            return new ResponseEntity<List<CarModelDTO>>(carModelRepository.getAllCarModel(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<CarModelDTO> findById(Long id) {
        try {

            return new ResponseEntity<>(carModelRepository.findBy_Id(id), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new CarModelDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<CarModel> save(CarModel CarModel) {
        try {
            return new ResponseEntity<>(carModelRepository.save(CarModel), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new CarModel(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        carModelRepository.deleteById(id);
    }


}
