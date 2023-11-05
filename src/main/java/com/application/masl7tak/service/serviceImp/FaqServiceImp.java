package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.FaqRepository;
import com.application.masl7tak.Repository.ReplacementRepository;
import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Faq;
import com.application.masl7tak.model.Replacement;
import com.application.masl7tak.model.User;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplacementRepository replacementRepository;

    @Override
    public ResponseEntity<Object> findAll() {
        try {
        // where have a answer
            return new ResponseEntity<>(faqRepository.findAllWithFilter(), HttpStatus.OK);

        } catch (Exception exception) {

            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),106), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        try {

            return new ResponseEntity<>(faqRepository.findById(id).orElse(null), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),108), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<Object> save(Faq faq) {
        try {
            return new ResponseEntity<>(faqRepository.save(faq), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),109), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void deleteById(Long id) {
        faqRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> support(String name, String email, String questionEn, int status) {
        if (!email.equals(jwtAuthFilter.getCurrentUser()))
            email+=" - " +jwtAuthFilter.getCurrentUser();
        try {
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();

            return new ResponseEntity<>(faqRepository.save(new Faq(name,email,questionEn,status,user.getId())), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),110), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Object> findAllSupport() {
        try {
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();
            if (user.getRole().equals("admin")){
            return new ResponseEntity<>(faqRepository.findAllSupport(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(faqRepository.findFaqByUser_idIs(user.getId()), HttpStatus.OK);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),111), HttpStatus.BAD_REQUEST);

        }
    }


    @Override
    public ResponseEntity<Object> points(Long userId) {
        try {
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();
          Replacement replacement = replacementRepository.findById(1L).orElseThrow();
            userRepository.updatePoints(0,user.getId());
            if (user.getPoints()>=replacement.getMin_no_of_points_to_change()){
            return new ResponseEntity<>(faqRepository.save(new Faq(user.getName(),user.getEmail(),user.getName()+" : "+"اريد استبدال نقاطي وهما "+user.getPoints()+" نقطة ",0,user.getId())), HttpStatus.OK);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),105), HttpStatus.BAD_REQUEST);

    }
        return new ResponseEntity<>(Constants.responseMessage("Can't exchange you point yet",106), HttpStatus.BAD_REQUEST);

    }


}
