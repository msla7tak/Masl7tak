package com.application.masl7tak.dto;


import com.application.masl7tak.model.Insurance;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class InsuranceDTO {
    private Long id;

    private String car_type;

    private Long car_brand_id;

    private Long car_model_id;

    private String car_price;

    private String insurance_logo;
    private String insurance_contact;
    private String insurance_type;
    private String kilometers;

    private String made_in;

    private String owner_age;

    private String phone_number;

    private String commission;

    private String insurance_price;
    private String insurance_price_ar;

    private String insurance_period;

    private String payment_status;
    private Long user_id;
    private  String status;
    private String invoice_id;

    public InsuranceDTO(Long id, String car_type, Long car_brand_id, Long car_model_id, String car_price, String kilometers, String made_in, String owner_age,
                        String phone_number, String commission, String insurance_price,String insurance_price_ar, String insurance_period,
                        String payment_status, Long user_id,String insurance_logo, String insurance_contact, String insurance_type,String status,String invoice_id) {
        this.id = id;
        this.car_type = car_type;
        this.car_brand_id = car_brand_id;
        this.car_model_id = car_model_id;
        this.car_price = car_price;
        this.kilometers = kilometers;
        this.made_in = made_in;
        this.owner_age = owner_age;
        this.phone_number = phone_number;
        this.commission = commission;
        this.insurance_price = insurance_price;
        this.insurance_price_ar = insurance_price_ar;
        this.insurance_period = insurance_period;

        this.insurance_logo = insurance_logo;
        this.insurance_contact = insurance_contact;
        this.insurance_type = insurance_type;
        this.payment_status = payment_status;
        this.user_id = user_id;
        this.status = status;
        this.invoice_id = invoice_id;
    }

    public InsuranceDTO() {

    }

    public InsuranceDTO(Insurance row) {
        this.id = row.getId();
        this.car_type = row.getCar_type();
        this.car_brand_id = row.getCar_brand();
        this.car_model_id = row.getCar_model();
        this.car_price =row.getCar_price();
        this.kilometers = row.getKilometers();
        this.made_in = row.getMade_in();
        this.owner_age = row.getOwner_age();
        this.phone_number = row.getPhone_number();
        this.commission = row.getCommission();
        this.insurance_price = row.getInsurance_price();
        this.insurance_period = row.getInsurance_period();
        this.payment_status = row.getPayment_status();
        this.user_id = row.getUser().getId();
        this.insurance_logo = row.getInsurance_logo();
        this.insurance_contact = row.getInsurance_contact();
        this.insurance_type = row.getInsurance_type();
        this.insurance_price_ar = row.getInsurance_price_ar();
    }
}
