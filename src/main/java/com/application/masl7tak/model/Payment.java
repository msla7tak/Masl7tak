package com.application.masl7tak.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "readme_id")
    private Integer readme_id;

    @Column(name = "service_name")
    private Integer service_name;

    @Column(name = "service_id")
    private Integer service_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "business_id")
    private Integer business_id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "promo_code_discount")
    private Double promo_code_discount;

    @Column(name = "paid_amount")
    private Double paid_amount;

    @Column(name = "tran_ref")
    private String tranRef;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_withdrawal")
    private Integer is_withdrawal;


    @Column(name = "created_at")
    private String createdAt;



    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
