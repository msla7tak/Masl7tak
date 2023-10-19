package com.application.masl7tak.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class UserDTO {
    private Long id;
    private Long business_id;
    private String image;
    private String name;
    private String contactNumber;
    private String email;
    private String role;
    private String status;
    private Integer points;
    private int carBrand;
    private int carModel;
    private String facebook_id;
    private String gmail_id;
    private String token;
    private String invitation_code;
    public UserDTO(Long id, Long business_id, String image, String name, String contactNumber, String email,
                   String role, String status, Integer points, int carBrand, int carModel,
                   String facebook_id, String gmail_id,String invitation_code) {
        this.id = id;
        this.business_id = business_id;
        this.image = image;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.role = role;
        this.status = status;
        this.points = points;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.facebook_id = facebook_id;
        this.gmail_id = gmail_id;
        this.invitation_code = invitation_code;
    }
    public UserDTO(String name) {
        this.name = name;
    }

    public UserDTO(Long id,String name) {
        this.id = id;
        this.name = name;

    }
}
