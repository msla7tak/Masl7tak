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
@Table(name="replacement")
public class Replacement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="insurance_val")
    private String insurance_val;
    @Column(name="start_discount_val")
    private String start_discount_val;
    @Column(name="point_for_registration")
    private int point_for_registration;
    @Column(name="point_for_invitation")
    private int point_for_invitation;
    @Column(name="min_no_of_points_to_change")
    private int min_no_of_points_to_change;


    @Column(name = "terms_conditions",length = 30000)
    private String termsConditions;

    @Column(name = "policy",length = 30000)
    private String policy;


}
