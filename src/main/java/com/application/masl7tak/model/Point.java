package com.application.masl7tak.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "point")
public class Point implements  Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "point")
    private int point;
    @Column(name = "promo_code")
    private String promo_code;
    @Column(name = "discount_value")
    private double discountValue;
    @Column(name = "valid_until")
    private String validUntil;





}
