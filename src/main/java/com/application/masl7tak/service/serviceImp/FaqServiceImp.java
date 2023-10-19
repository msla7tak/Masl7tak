package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.FaqRepository;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Faq;
import com.application.masl7tak.service.FaqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class FaqServiceImp implements FaqService {
    @Autowired
    private  FaqRepository faqRepository;
    @Autowired
    private  JwtAuthFilter jwtAuthFilter;


    @Override
    public ResponseEntity<List<Faq>> findAll() {
        try {
        // where have a answer
            return new ResponseEntity<List<Faq>>(faqRepository.findAllWithFilter(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Faq> findById(Long id) {
        try {

            return new ResponseEntity<Faq>(faqRepository.findById(id).orElse(null), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new Faq(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Faq> save(Faq faq) {
        try {
            return new ResponseEntity<>(faqRepository.save(faq), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Faq(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        faqRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Faq> support(String name, String email, String questionEn, int status) {
        if (!email.equals(jwtAuthFilter.getCurrentUser()))
            email+=" - " +jwtAuthFilter.getCurrentUser();
        try {

            return new ResponseEntity<>(faqRepository.save(new Faq(name,email,questionEn,status)), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Faq(), HttpStatus.INTERNAL_SERVER_ERROR);    }

    @Override
    public ResponseEntity<List<Faq>> findAllSupport() {
        try {

            return new ResponseEntity<List<Faq>>(faqRepository.findAllSupport(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);    }


}
