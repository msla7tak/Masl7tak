package com.application.masl7tak.dto;

import com.application.masl7tak.model.Insurance;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class NotificationDTO {

    private Long id;
    private  String title;
    private  String creationDate;
    private  String description;
    private  String statusReviewed;
    private  String status;
    private  String type;
    private Long insurances_id;

    public NotificationDTO(Long id, String title, String creationDate, String description, String statusReviewed,Long insurances_id,String status,String type) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
        this.statusReviewed = statusReviewed;
        this.insurances_id = insurances_id;
        this.status = status;
        this.type = type;
    }
}
