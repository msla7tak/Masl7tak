package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.PaymentRepository;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.model.Payment;

import com.application.masl7tak.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional
public class PaymentServiceImp implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<Object> findAll() {
        try {


            return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),142), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        try {

            return new ResponseEntity<>(paymentRepository.findById(id), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),142), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> save(Payment payment) {
        try {
            LocalDate today = LocalDate.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(dateFormat);
            payment.setCreatedAt(formattedDate);

            payment=  paymentRepository.save(payment);
            return new ResponseEntity<>(paymentRepository.findById(payment.getId()), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),142), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> findUserCoupons(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findBusinessCoupons(Long businessId, String date) {
        return null;
    }

//    @Override
//    public ResponseEntity<Object> findUserCoupons(Long userId) {
//        try {
//
//            return new ResponseEntity<>(paymentRepository.findUserPayment(userId), HttpStatus.OK);
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//
//            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 109), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setReadme_id(paymentDetails.getReadme_id());
        payment.setService_name(paymentDetails.getService_name());
        payment.setUser_name(paymentDetails.getUser_name());
        payment.setBusiness_name(paymentDetails.getBusiness_name());
        payment.setService_id(paymentDetails.getService_id());
        payment.setUser_id(paymentDetails.getUser_id());
        payment.setBusiness_id(paymentDetails.getBusiness_id());
        payment.setPromo_code(paymentDetails.getPromo_code());
        payment.setAmount(paymentDetails.getAmount());
        payment.setPromo_code_discount(paymentDetails.getPromo_code_discount());
        payment.setReadme_discount(paymentDetails.getReadme_discount());
        payment.setPaid_amount(paymentDetails.getPaid_amount());
        payment.setTranRef(paymentDetails.getTranRef());
        payment.setType(paymentDetails.getType());
        payment.setStatus(paymentDetails.getStatus());
        payment.setIs_withdrawal(paymentDetails.getIs_withdrawal());
        payment.setCreatedAt(paymentDetails.getCreatedAt());

        return paymentRepository.save(payment);
    }

//    @Override
//    public ResponseEntity<Object> findBusinessCoupons(Long businessId, String date) {
//        try {
//            if (date.equals("")) {
//                return new ResponseEntity<>(paymentRepository.findBusinessPaymentIDs(businessId), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(paymentRepository.findBusinessPayment(businessId, date), HttpStatus.OK);
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 106), HttpStatus.BAD_REQUEST);
//        }
//    }


}
