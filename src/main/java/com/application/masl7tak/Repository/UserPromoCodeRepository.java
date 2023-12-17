package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.model.Category;
import com.application.masl7tak.model.UserPromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPromoCodeRepository extends JpaRepository<UserPromoCode,Long> {


    @Query("SELECT  C FROM UserPromoCode C  where C.promoCode_Id= :promo_code and C.userId= : user_id")

    UserPromoCode findUserById(Long promo_code, Long user_id);
}
