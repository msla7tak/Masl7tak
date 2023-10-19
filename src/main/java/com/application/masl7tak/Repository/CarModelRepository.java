package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.CarBrandDTO;
import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.model.CarBrand;
import com.application.masl7tak.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    @Query("select C from CarModel C where C.id=:id")
    CarModelDTO findBy_Id(@Param("id") Long id);

//(SELECT C.name FROM Category C WHERE C.id = S.category.id)
    @Query(value = "SELECT new com.application.masl7tak.dto.CarModelDTO(C.id,C.name, B.id , B.name ) " +
            " FROM  CarModel C left  join  CarBrand B where C.car_brand.id= B.id")
    List<CarModelDTO> getAllCarModel();
}