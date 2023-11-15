package com.application.masl7tak.Repository;

import com.application.masl7tak.model.BusinessIdEntity;
import com.application.masl7tak.model.CarBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandEntityRepository extends JpaRepository<CarBrandEntity, Long> {
}