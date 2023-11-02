package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.PromoCodeRepository;
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
    public ResponseEntity<List<PromoCode>> findAll() {
        try {
         
            return new ResponseEntity<List<PromoCode>>(promoCodeRepository.findAll(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<PromoCode> findById(Long id) {
        try {

            return new ResponseEntity<>(promoCodeRepository.findById(id).orElseThrow(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new PromoCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<PromoCode> save(PromoCode PromoCode) {
        try {
            LocalDate today = LocalDate.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(dateFormat);
            PromoCode.setCreationDate(formattedDate);
            PromoCode.setIs_available("true");
            return new ResponseEntity<>(promoCodeRepository.save(PromoCode), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new PromoCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        promoCodeRepository.deleteById(id);
    }


}
