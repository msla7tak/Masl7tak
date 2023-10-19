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
@Table(name = "city")
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_ar")
    private String name_ar;
    @Column(name = "name_en")
    private String name_en;


    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name="region_id", referencedColumnName = "id")
    public Region region;


}
