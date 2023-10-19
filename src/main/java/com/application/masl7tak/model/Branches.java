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
@Table(name = "branches")
public class Branches implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;
    @Column(name = "locationLink")
    private String locationLink;
    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "openTime")
    private String openTime;
    @Column(name = "closureTime")
    private String closureTime;
    @Column(name = "city_id")
    private Long city_id;



    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", referencedColumnName = "id")
    public Business business;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    public Region region;


}
