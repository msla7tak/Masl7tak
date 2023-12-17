package com.application.masl7tak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_promo_code")
public class UserPromoCode {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long promoCode_Id;
    public UserPromoCode(Long userId, Long promoCode_Id) {
        this.userId = userId;
        this.promoCode_Id = promoCode_Id;
    }

}


