package com.application.masl7tak.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesFilter {
    private int offset;
    private Long eventId;
    private Long categoryId;
    private Long brandId;
    private Long modelId;
    private Long businessId;
    private Long regionId;
    private Double discountMinVal;
    private Double discountMaxVal;
    private Long productId;
    private Float rate;
    private String searchKey;

}
