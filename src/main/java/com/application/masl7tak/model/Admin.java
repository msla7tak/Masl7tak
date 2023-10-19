package com.application.masl7tak.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin implements  Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "admin")
//    private List<Readme> readme;
//    @OneToMany(mappedBy = "admin")
//    private List<Insurance> insurance;
//    @OneToMany(mappedBy = "admin")
//    private List<Business> businesses;
//    @OneToMany(mappedBy = "admin")
//    private List<Services> services;


}
