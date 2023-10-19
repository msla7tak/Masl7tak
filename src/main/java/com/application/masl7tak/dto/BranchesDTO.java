package com.application.masl7tak.dto;

import com.application.masl7tak.model.Business;
import com.application.masl7tak.model.Region;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class BranchesDTO {

    private Long id;
    private String address;
    private String locationLink;
    private String phone_number;
    private String openTime;
    private String closureTime;


    public Long business_id;

    public RegionDTO region;


    public BranchesDTO(Long id, String address, String locationLink, String phone_number, String openTime,
                       String closureTime, Long business_id, Long region_id, String name_ar, String name_en) {
        this.id = id;
        this.address = address;
        this.locationLink = locationLink;
        this.phone_number = phone_number;
        this.openTime = openTime;

        this.closureTime = closureTime;
        this.business_id = business_id;
        this.region = new RegionDTO(region_id, name_ar, name_en);
    }
}
