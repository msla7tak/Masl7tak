package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.CarBrandDTO;
import com.application.masl7tak.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {


    @Query("SELECT DISTINCT C FROM CarBrand C LEFT JOIN FETCH C.car_model  where C.id=:id")
    List<CarBrand> findBy_Id(@Param("id") Long id);



    @Query(value = "SELECT new com.application.masl7tak.dto.CarBrandDTO(C.id,C.name) " +
            " FROM  CarBrand C")
    List<CarBrandDTO> getAllCarBrand();

    @Query("SELECT DISTINCT C FROM CarBrand C LEFT JOIN FETCH C.car_model")
    List<CarBrand> findAllWithCarModels();
}