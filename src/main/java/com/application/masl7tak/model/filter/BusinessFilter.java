package com.application.masl7tak.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessFilter {
    private int offset;
    private Long categoryId;
    private Long regionId;
    private Float rate;
    private String searchKey;

}
