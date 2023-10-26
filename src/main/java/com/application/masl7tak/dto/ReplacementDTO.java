package com.application.masl7tak.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class ReplacementDTO {


    private Long id;

    private String title;

    private String sub_title;
    private  int point_for_invitation;
    private  int min_no_of_points_to_change;
    private  int redeemed_points;
    ;

    private String type;

    private String description;
    private String termsConditions;


    public ReplacementDTO(Long id, String title, String sub_title, String type, String description, String termsConditions,int point_for_invitation,int min_no_of_points_to_change,int redeemed_points) {
        this.id = id;
        this.title = title;
        this.sub_title = sub_title;
        this.type = type;
        this.description = description;
        this.termsConditions = termsConditions;
        this.point_for_invitation = point_for_invitation;
        this.min_no_of_points_to_change = min_no_of_points_to_change;
        this.redeemed_points = redeemed_points;
    }
}
