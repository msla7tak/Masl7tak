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
@Table(name = "region")
public class Region implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_ar")
    private String name_ar;
    @Column(name = "name_en")
    private String name_en;
    @OneToMany(mappedBy = "region", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Branches> branches;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<City> cityList;


}
