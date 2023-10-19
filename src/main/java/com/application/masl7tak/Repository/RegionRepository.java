package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.dto.RegionDTO;
import com.application.masl7tak.model.Branches;
import com.application.masl7tak.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    @Query(value = "SELECT new com.application.masl7tak.dto.RegionDTO(R.id, R.name_ar, R.name_en) FROM Region R")
    List<RegionDTO> getAllRegion();
    @Query(value = "SELECT R FROM Region R where R.id=:id")
    List<Region> findRegionDTOById(Long id);

}