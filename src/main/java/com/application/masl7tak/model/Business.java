package com.application.masl7tak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;




@NamedQuery(name = "Business.findByEmail", query = "select B from Business B where B.email=:email")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "business")
public class Business implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "subscriptionType")
    private String subscriptionType;

    @Column(name = "description")
    private String description;

    @Column(name = "logo")
    private String logo;
    @Column(name = "start_discount_val")
    private double start_discount_val;
    @Column(name = "visits_num")
    private double visits_num;

    @Column(name = "rate")
    private float rate;
    @Column(name = "working_days")
    private String working_days;
    @Column(name = "terms_conditions",length = 30000)
    private String termsConditions;

//    @Column(name = "work_schedule")
//    private String work_schedule;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products> products;
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Branches> branches;
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Services> services;
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventOffers> eventOffers;
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    public Business(Long id) {
        this.id = id;
    }
}
