package com.application.masl7tak.Repository;

import com.application.masl7tak.model.CarBrandEntity;
import com.application.masl7tak.model.CarModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelEntityRepository extends JpaRepository<CarModelEntity, Long> {
}