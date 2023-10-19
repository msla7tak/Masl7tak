package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.RegionRepository;
import com.application.masl7tak.dto.CarBrandDTO;
import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.dto.CityDTO;
import com.application.masl7tak.dto.RegionDTO;
import com.application.masl7tak.model.CarBrand;
import com.application.masl7tak.model.Region;
import com.application.masl7tak.service.RegionService;
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
public class RegionServiceImp implements RegionService {
    private final RegionRepository regionRepository;
    @Autowired
    public RegionServiceImp(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    private RegionDTO regionToDTO(Region region) {
        List<CityDTO> cityIds = region.getCityList().stream()
                .map(city -> new CityDTO(city.getId(), city.getName_ar(),city.getName_en()))
                .collect(Collectors.toList());
        return new RegionDTO(region.getId(), region.getName_ar(), region.getName_en(), cityIds);
    }
    @Override
    public ResponseEntity<List<RegionDTO>> findAll() {
        try {

            List<Region> regions = regionRepository.findAll();
            List<RegionDTO> regionDTOs = regions.stream().map(this::regionToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(regionDTOs);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<RegionDTO> findById(Long id) {
        try {


            List<Region> regions =   regionRepository.findRegionDTOById(id);
            List<RegionDTO> regionDTOs = regions.stream().map(this::regionToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<RegionDTO>(regionDTOs.get(0), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new RegionDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Region> save(Region category) {
        try {
            return new ResponseEntity<>(regionRepository.save(category), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Region(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }


}
