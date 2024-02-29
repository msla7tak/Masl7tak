package com.application.masl7tak.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventOffers")
public class EventOffers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private int type = 0;

    @Column(name = "event_title ")
    private String event_title;
    @Column(name = "event_sub_title ")
    private String event_sub_title;

    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "eventOffers", cascade = CascadeType.ALL)
    private List<Services> services;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", referencedColumnName = "id")
    public Business business;


//    @Column(name = "description")
//    private String description;

//    @ManyToOne
//    @JoinColumn(name = "eventOffers")
//    private Products products;

//    @Column(name="creation_date")
//    private java.sql.Date creation_date;
//
//    @Column(name="valid_until")
//    private java.sql.Date valid_until;

    public EventOffers(Long id) {
        this.id = id;
    }


    // getters and setters
}