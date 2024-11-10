package com.application.masl7tak.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Transactional
public class PaymentDTO   {


    private Long id;

    private Integer readme_id;

    private Integer service_name;

    private Integer service_id;

    private Integer user_id;

    private Integer business_id;

    private String amount;

    private Double promo_code_discount;

    private Double paid_amount;

    private String tranRef;

    private Integer type;

    private Integer status;

    private Integer is_withdrawal;


    private String createdAt;


    public PaymentDTO(Long id, Integer readme_id, Integer service_name, Integer service_id, Integer user_id,
                      Integer business_id, String amount, Double promo_code_discount,
                      Double paid_amount, String tranRef, Integer type,
                      Integer status, Integer is_withdrawal, String createdAt) {
        this.id = id;
        this.readme_id = readme_id;
        this.service_name = service_name;
        this.service_id = service_id;
        this.user_id = user_id;
        this.business_id = business_id;
        this.amount = amount;
        this.promo_code_discount = promo_code_discount;
        this.paid_amount = paid_amount;
        this.tranRef = tranRef;
        this.type = type;
        this.status = status;
        this.is_withdrawal = is_withdrawal;
        this.createdAt = createdAt;
    }
}
