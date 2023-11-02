package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.model.Category;
import com.application.masl7tak.model.PromoCode;
import com.application.masl7tak.model.Services;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
  @Modifying
    @Query("update PromoCode s set s.readme_num = (s.readme_num +1)  where s.id = :id")
    void readme_num(Long id);


    @Modifying
    @Query("update PromoCode s set s.is_available = 'false'  where s.id = :id and s.readme_num=s.max_usage")
    void isAvailable(Long id);



}

