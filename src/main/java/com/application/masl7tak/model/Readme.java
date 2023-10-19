package com.application.masl7tak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "readme")
public class Readme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "schedule_date")
    private String schedule_date;
    @Column(name = "schedule_time")
    private String schedule_time;
    @Column(name = "readme_date")
    private String readme_date;
    @Column(name = "business_id")
    private Long business_id;
    @Column(name = "business_branch")
    private Long business_branch;
    @Column(name = "document_path")
    private String documentPath;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "total_invoice")
    private String total_invoice;
    @Column(name = "state_name")
    private String stateName;
    @Column(name = "rate")
    private float rate;
    @Column(name = "comment")
    private String comment;
    @Column(name = "confirm_date")
    private int confirm_date;
    @Column(name = "confirm_invoice")
    private int confirm_invoice;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id", referencedColumnName = "id")
    private Services services;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


//    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
//    @JoinColumn(name="myCoupons_id", referencedColumnName="id")
//    private MyCoupons myCoupons;


}
