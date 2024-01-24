package com.application.masl7tak.dto;

import com.application.masl7tak.model.Branches;
import com.application.masl7tak.model.CarBrand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDTO {

    private Long id;
    private String name;
    private String email;
    private String status;
    private String subscriptionType;
    private String description;
    private Long service_type_id;
    private String logo;
    private String working_days;
    private Long productCount;
    private String termsConditions;

    private float rate;
    private Long branches;
    private double start_discount_val;
    private double visits_num;



    public BusinessDTO(Long id, String name,  String email, String status, String subscriptionType,
                       String description, String logo, Long productCount,
                       double start_discount_val, float rate, Long service_type, Long branches,double visits_num,String working_days) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.subscriptionType = subscriptionType;
        this.description = description;
        this.logo = logo;
        this.working_days = working_days;
        this.start_discount_val = start_discount_val;
        this.productCount = productCount;
        this.branches = branches;
        this.service_type_id = service_type;
        this.rate = rate;
        this.visits_num = visits_num;
    }    public BusinessDTO(Long id, String name,  String email, String status, String subscriptionType,
                       String description, String logo, Long productCount,
                       double start_discount_val, float rate, Long service_type, Long branches,double visits_num,String working_days,String termsConditions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.subscriptionType = subscriptionType;
        this.description = description;
        this.logo = logo;
        this.working_days = working_days;
        this.start_discount_val = start_discount_val;
        this.productCount = productCount;
        this.branches = branches;
        this.service_type_id = service_type;
        this.rate = rate;
        this.visits_num = visits_num;
        this.termsConditions = termsConditions;
    }

    public BusinessDTO(Long id, String name,  String email, String status, String subscriptionType,
                       String description, String logo ,double start_discount_val,String working_days) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.working_days = working_days;
        this.status = status;
        this.subscriptionType = subscriptionType;
        this.description = description;
        this.logo = logo;
        this.start_discount_val = start_discount_val;
    }

    public BusinessDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
