package com.application.masl7tak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promo_code")
public class PromoCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name=" max_usage")
    private int  max_usage;
    @Column(name="readme_num")
    private double readme_num;
    @Column(name = "valid_until")
    private String validUntil;
    @Column(name = "creation_date")
    private String creationDate;
    @Column(name = "discount_value")
    private double discountValue;
    @Column(name = "is_available")
    private String is_available;
    @OneToMany(mappedBy = "promoCode", cascade = CascadeType.ALL)
    private List<BusinessIdEntity> businessIds;
    public List<Long> getAllBusinessIds() {
        if (businessIds == null) {
            return Collections.emptyList();
        }

        return businessIds.stream()
                .map(BusinessIdEntity::aLongBusinessId)
                .collect(Collectors.toList());
    }


}
