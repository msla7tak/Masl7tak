package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.InsuranceDTO;
import com.application.masl7tak.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {


    @Query("SELECT new com.application.masl7tak.dto.InsuranceDTO( " +
            "    I.id,  " +
            "    I.car_type,  " +
            "    I.car_brand, " +
            "    I.car_model,  " +
            "    I.car_price,  " +
            "    I.kilometers,  " +
            "    I.made_in,  " +
            "    I.owner_age,  " +
            "    I.phone_number,  " +
            "    I.commission,  " +
            "    I.insurance_price,  " +
            "    I.insurance_price_ar,  " +
            "    I.insurance_period,  " +
            "    I.payment_status,  " +
            "    I.user.id,I.insurance_logo, I.insurance_contact, I.insurance_type, I.status, I.invoice_id " +
            ") " +
            "FROM Insurance I ")
    List<InsuranceDTO>findAllInsurance();

    @Query("SELECT new com.application.masl7tak.dto.InsuranceDTO( " +
            "    I.id,  " +
            "    I.car_type,  " +
            "    I.car_brand, " +
            "    I.car_model,  " +
            "    I.car_price,  " +
            "    I.kilometers,  " +
            "    I.made_in,  " +
            "    I.owner_age,  " +
            "    I.phone_number,  " +
            "    I.commission,  " +
            "    I.insurance_price,  " +
            "    I.insurance_price_ar,  " +
            "    I.insurance_period,  " +
            "    I.payment_status,  " +
            "    I.user.id,I.insurance_logo, I.insurance_contact, I.insurance_type , I.status, I.invoice_id" +
            ") " +
            "FROM Insurance I where I.id=:id")
    InsuranceDTO findInsuranceById(Long id);
    @Modifying
    @Query("UPDATE Insurance I  " +
            "SET I.insurance_logo = :insuranceLogo,  " +
            "    I.insurance_contact = :insuranceContact,  " +
            "    I.insurance_type = :insuranceType,  " +
            "    I.insurance_price = :insurancePrice,  " +
            "    I.insurance_period = :insurancePeriod,  " +
            "    I.commission = :commission,  " +
            "    I.insurance_price_ar = :insurancePriceAr  " +
            "WHERE I.id = :insuranceId  ")
    void AcceptOffer(String insuranceLogo, String insuranceContact, String insuranceType, String insurancePrice, String insurancePeriod, String commission, String insurancePriceAr, Long insuranceId);

    @Modifying
    @Query("UPDATE Insurance I  " +
            "SET I.invoice_id = :invoice_id, " +
            " I.status = :status " +
            "WHERE I.id = :insuranceId  ")
    void updateInvoice(String invoice_id, Long insuranceId,String status);





}