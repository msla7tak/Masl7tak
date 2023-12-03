package com.application.masl7tak.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@Table(name="insurance")
public class Insurance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_type")
    private String car_type;

    @Column(name = "car_brand")
    private Long car_brand;
    @Column(name = "car_model")
    private Long car_model;
    @Column(name = "car_price")
    private String car_price;

    @Column(name = "kilometers")
    private String kilometers;
    @Column(name = "made_in")
    private String made_in;
    @Column(name = "invoice_id")
    private String invoice_id;
    @Column(name = "status")
    private String status;
    @Column(name = "owner_age")
    private String owner_age;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "commission")
    private String commission;
    @Column(name = "insurance_logo")
    private String insurance_logo;
    @Column(name = "insurance_contact")
    private String insurance_contact;
    @Column(name = "insurance_type")
    private String insurance_type;
    @Column(name = "insurance_price")
    private String insurance_price;
    @Column(name = "insurance_price_ar")
    private String insurance_price_ar;
    @Column(name = "insurance_period")
    private String insurance_period;
    @Column(name = "payment_status ")
    private String payment_status;
    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notification;



}
