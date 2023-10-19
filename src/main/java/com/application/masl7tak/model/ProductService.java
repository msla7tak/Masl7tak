package com.application.masl7tak.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {
    private Long id;
    private Long products_id;
    private String name;
    private String description;
    private double discountValue;
    private String images;
    private String creationDate;
    private String validUntil;
    private String is_available;
    private int quantity;
    private Long carBrand;
    private Long carModel;
    private int max_usage;
    private float rate;

    public Business business;
    public Long businessId;
    public Long eventId;
    public Long categoryId;
    private Category category;
}
