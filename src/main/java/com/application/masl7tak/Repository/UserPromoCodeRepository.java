package com.application.masl7tak.Repository;

import com.application.masl7tak.model.UserPromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPromoCodeRepository extends JpaRepository<UserPromoCode,Long> {


    @Query("SELECT C FROM UserPromoCode C WHERE C.promoCode_Id = :promo_code and C.userId = :user_id")
    List<UserPromoCode> findUserById(@Param("promo_code") Long promoCodeId, @Param("user_id") Long userId);

}
