package com.application.masl7tak.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;





@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "business_id")
    private Long business_id;
    @Column(name = "name")
    private String name;
    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "invitation_code")
    private String invitation_code;
    @Column(name = "inviter_code")
    private String inviter_code;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "image")
    private String image;

    @Column(name = "role")
    private String role;
    @Column(name = "car_brand")
    private int carBrand;
    @Column(name = "car_model")
    private int carModel;
    @Column(name = "facebook_id")
    private String facebook_id;
    @Column(name = "firebase_token")
    private String firebase_token;
    @Column(name = "gmail_id")
    private String gmail_id;
    @Column(name = "creation_date")
    private String creationDate;
    @Column(name = "points")
    private Integer points=0;
    //    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Role role;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Coupons> coupons;


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<BusinessAccount> businessAccounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Readme> readme;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payment;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Insurance> insurance;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MyCoupons> myCoupons;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
