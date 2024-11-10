package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.PaymentRepository;
import com.application.masl7tak.Repository.PromoCodeRepository;
import com.application.masl7tak.Repository.UserPromoCodeRepository;
import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.model.BusinessIdEntity;
import com.application.masl7tak.model.Payment;
import com.application.masl7tak.model.PromoCode;
import com.application.masl7tak.model.User;
import com.application.masl7tak.service.PaymentService;
import com.application.masl7tak.service.PromoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PaymentServiceImp implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<Object> findAll() {
        try {
         
            return new ResponseEntity<>(paymentRepository.findAllDto(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),142), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        try {

            return new ResponseEntity<>(paymentRepository.findBy_Id(id), HttpStatus.OK);

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
            return new ResponseEntity<>(paymentRepository.findBy_Id(payment.getId()), HttpStatus.OK);
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
        try {

            return new ResponseEntity<>(paymentRepository.findUserPayment(userId), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();

            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 109), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Object> findBusinessCoupons(Long businessId, String date) {
        try {
            if (date.equals("")) {
                return new ResponseEntity<>(paymentRepository.findBusinessPaymentIDs(businessId), HttpStatus.OK);
            }
            return new ResponseEntity<>(paymentRepository.findBusinessPayment(businessId, date), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 106), HttpStatus.BAD_REQUEST);
        }
    }


}
