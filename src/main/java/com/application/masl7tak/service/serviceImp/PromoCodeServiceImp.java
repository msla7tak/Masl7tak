package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.PromoCodeRepository;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.PromoCodeDTO;
import com.application.masl7tak.model.BusinessIdEntity;
import com.application.masl7tak.model.PromoCode;
import com.application.masl7tak.service.PromoCodeService;
import com.application.masl7tak.service.PromoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PromoCodeServiceImp implements PromoCodeService {
    @Autowired
    private  PromoCodeRepository  promoCodeRepository;

    @Override
    public ResponseEntity<Object> findAll() {
        try {
         
            return new ResponseEntity<>(promoCodeRepository.findAllDto(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),142), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        try {

            return new ResponseEntity<>(promoCodeRepository.findBy_Id(id), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),142), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<Object> save(PromoCode PromoCode) {
        try {
            LocalDate today = LocalDate.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(dateFormat);
            PromoCode.setCreationDate(formattedDate);

            for (BusinessIdEntity businessIdEntity  :   PromoCode.getBusinessIds()) {
                businessIdEntity.setPromoCode(PromoCode);
            }
            PromoCode.setIs_available("true");
          PromoCode=  promoCodeRepository.save(PromoCode);
            return new ResponseEntity<>(promoCodeRepository.findBy_Id(PromoCode.getId()), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),142), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void deleteById(Long id) {
        promoCodeRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> expired(String code,Long business_id) {
        try {
            PromoCode promoCode = promoCodeRepository.findByCode(code).orElse(null);

            if (promoCode!=null) {
                List<Long> allBusinessIds = promoCode.getAllBusinessIds();
                if (allBusinessIds.contains(business_id)) {
                    if (promoCode.getReadme_num() < promoCode.getMax_usage()) {
                        return new ResponseEntity<>(Constants.responseMessage( promoCode.getDiscountValue(), 200), HttpStatus.OK);
                    }

                    return new ResponseEntity<>(Constants.responseMessage("Promo code not valid ", 141), HttpStatus.BAD_REQUEST);

                }
                return new ResponseEntity<>(Constants.responseMessage("Promo code not valid ", 140), HttpStatus.BAD_REQUEST);

            }
                return new ResponseEntity<>(Constants.responseMessage("Promo code not found ", 140), HttpStatus.BAD_REQUEST);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),142), HttpStatus.BAD_REQUEST);

        }
    }


}
