package com.application.masl7tak.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;

import java.io.Serializable;

@Data
@Entity
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "business_account")
public class BusinessAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
}
