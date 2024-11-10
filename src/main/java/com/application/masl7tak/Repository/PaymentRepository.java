package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.PaymentDTO;
import com.application.masl7tak.dto.PaymentDTO;
import com.application.masl7tak.model.Payment;
import com.application.masl7tak.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


  @Query("SELECT new com.application.masl7tak.dto.PaymentDTO(P.id, P.readme_id, P.service_name, P.service_id, P.user_id, " +
          "P.business_id, P.amount, P.promo_code_discount, P.paid_amount, P.tranRef, P.type, P.status, P.is_withdrawal, P.createdAt) " +
          "FROM Payment P")
  List<PaymentDTO> findAllDto();

  @Query("SELECT new com.application.masl7tak.dto.PaymentDTO(P.id, P.readme_id, P.service_name, P.service_id, P.user_id, " +
          "P.business_id, P.amount, P.promo_code_discount, P.paid_amount, P.tranRef, P.type, P.status, P.is_withdrawal, P.createdAt) " +
          "FROM Payment P WHERE P.id = :id")
  PaymentDTO findBy_Id(Long id);

  @Query("SELECT new com.application.masl7tak.dto.PaymentDTO( " +
          "    P.id, " +
          "    P.readme_id, " +
          "    P.service_name, " +
          "    P.service_id, " +
          "    P.user_id, " +
          "    P.business_id, " +
          "    P.amount, " +
          "    P.promo_code_discount, " +
          "    P.paid_amount, " +
          "    P.tranRef, " +
          "    P.type, " +
          "    P.status, " +
          "    P.is_withdrawal, " +
          "    P.createdAt) " +
          "FROM Payment P " +
          "WHERE P.user_id = :userId " +
          "ORDER BY P.id DESC")
  List<PaymentDTO> findUserPayment(Long userId);

  @Query("SELECT new com.application.masl7tak.dto.PaymentDTO( " +
          "    P.id, " +
          "    P.readme_id, " +
          "    P.service_name, " +
          "    P.service_id, " +
          "    P.user_id, " +
          "    P.business_id, " +
          "    P.amount, " +
          "    P.promo_code_discount, " +
          "    P.paid_amount, " +
          "    P.tranRef, " +
          "    P.type, " +
          "    P.status, " +
          "    P.is_withdrawal, " +
          "    P.createdAt) " +
          "FROM Payment P " +
          "WHERE P.business_id = :businessId " +
          "AND P.createdAt = :date " +
          "GROUP BY P.id " +
          "ORDER BY P.id DESC")
  List<PaymentDTO> findBusinessPayment(Long businessId, String date);

  @Query("SELECT new com.application.masl7tak.dto.PaymentDTO( " +
          "    P.id, " +
          "    P.readme_id, " +
          "    P.service_name, " +
          "    P.service_id, " +
          "    P.user_id, " +
          "    P.business_id, " +
          "    P.amount, " +
          "    P.promo_code_discount, " +
          "    P.paid_amount, " +
          "    P.tranRef, " +
          "    P.type, " +
          "    P.status, " +
          "    P.is_withdrawal, " +
          "    P.createdAt) " +
          "FROM Payment P " +
          "WHERE P.business_id = :businessId " +
          "GROUP BY P.id " +
          "ORDER BY P.id DESC")
  List<PaymentDTO> findBusinessPaymentIDs(Long businessId);


}

