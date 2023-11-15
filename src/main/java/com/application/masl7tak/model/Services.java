package com.application.masl7tak.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="services")
public class Services implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "discount_value")
    private double discountValue;
    @Column(name = "images")
    private String images;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "valid_until")
    private String validUntil;
    @Column(name = "is_available")
    private String is_available;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "car_brand")
    private Long carBrand;
    @Column(name = "car_model")
    private Long carModel;
    @Column(name="rate")
    private float rate;
    @Column(name="readme_num")
    private double readme_num;
    @Column(name=" visit_num")
    private double  visit_num;
    @Column(name=" max_usage")
    private int  max_usage;
    @Column(name="schedule_mode")
    private int  schedule_mode;

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private List<CarBrandEntity> carBrandEntities;
    public List<Long> getAllBrandIds() {
        if (carBrandEntities == null) {
            return Collections.emptyList();
        }

        return carBrandEntities.stream()
                .map(CarBrandEntity::aLongcarBrandId)
                .collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private List<CarModelEntity> carModelEntities;
    public List<Long> getAllModelIds() {
        if (carModelEntities == null) {
            return Collections.emptyList();
        }

        return carModelEntities.stream()
                .map(CarModelEntity::aLongcarModelId)
                .collect(Collectors.toList());
    }

//    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name = "products_id", referencedColumnName = "id")
    private Products products;

    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name="business_id", referencedColumnName = "id")
    public Business business;
    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    List<Readme> readme;

    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name="eventOffers_id", referencedColumnName = "id")
    public EventOffers eventOffers;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    private Category category;

}