package com.application.masl7tak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "business_ids")
public class BusinessIdEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "promo_code_id", referencedColumnName = "id")
    private PromoCode promoCode;

    private String businessId;
    public BusinessIdEntity(String businessId) {
        this.businessId = businessId;
    }
    public Long aLongBusinessId(){
        return Long.parseLong(this.businessId);
    }
}
