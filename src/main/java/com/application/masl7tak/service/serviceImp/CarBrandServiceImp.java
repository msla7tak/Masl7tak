package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.CarBrandRepository;
import com.application.masl7tak.dto.CarBrandDTO;
import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.model.CarBrand;
import com.application.masl7tak.service.CarBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CarBrandServiceImp implements CarBrandService {
   private final CarBrandRepository carBrandRepository;
    @Autowired
    public CarBrandServiceImp(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    // get name
    private CarBrandDTO carBrandToDTO(CarBrand carBrand) {
        List<CarModelDTO> carModelIds = carBrand.getCar_model().stream()
                .map(carModel -> new CarModelDTO(carModel.getId(), carModel.getName()))
                .collect(Collectors.toList());
        return new CarBrandDTO(carBrand.getId(), carBrand.getName(), carModelIds);
    }
    @Override
    public ResponseEntity<List<CarBrandDTO>> findAll() {
        try {

            List<CarBrand> carBrands = carBrandRepository.findAllWithCarModels();
            List<CarBrandDTO> carBrandDTOs = carBrands.stream().map(this::carBrandToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(carBrandDTOs);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<CarBrandDTO> findById(Long id) {
        try {
            List<CarBrand> carBrands = carBrandRepository.findBy_Id(id);
            CarBrandDTO carBrandDTOs = carBrands.stream().map(this::carBrandToDTO)
                    .collect(Collectors.toList()).get(0);
            return ResponseEntity.ok(carBrandDTOs);


        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new CarBrandDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<CarBrand> save(CarBrand carBrand) {
        try {
            return new ResponseEntity<>(carBrandRepository.save(carBrand), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new CarBrand(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        carBrandRepository.deleteById(id);
    }


}
