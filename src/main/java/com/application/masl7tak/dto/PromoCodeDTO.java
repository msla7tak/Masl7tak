package com.application.masl7tak.dto;

import com.application.masl7tak.model.BusinessIdEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Data
@Transactional
public class PromoCodeDTO  {
    private Long id;
    private String code;
    private int  max_usage;
    private double readme_num;
    private String validUntil;
    private String creationDate;
    private double discountValue;
    private String is_available;



    public PromoCodeDTO(Long id, String code, int max_usage, double readme_num,
                        String validUntil, String creationDate, double discountValue, String is_available) {
        this.id = id;
        this.code = code;
        this.max_usage = max_usage;
        this.readme_num = readme_num;
        this.validUntil = validUntil;
        this.creationDate = creationDate;
        this.discountValue = discountValue;
        this.is_available = is_available;
    }
}
