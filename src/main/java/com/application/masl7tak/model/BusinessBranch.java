package com.application.masl7tak.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessBranch {
    private Long id;
    private String name;
    private String email;
    private int status;
    private String subscriptionType;
    private String description;
    private String logo;
    private double start_discount_val;
    private float rate;
    private String termsConditions;
    private List<Branches> branches;
    private Category category;
    private String working_days;
}
