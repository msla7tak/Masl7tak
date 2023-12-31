package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.FaqRepository;
import com.application.masl7tak.Repository.NotificationRepository;
import com.application.masl7tak.Repository.ReplacementRepository;
import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Faq;
import com.application.masl7tak.model.Notification;
import com.application.masl7tak.model.Replacement;
import com.application.masl7tak.model.User;
import com.application.masl7tak.service.FaqService;
import com.application.masl7tak.utils.FBNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private FBNotificationService fbNotificationService;

    @Override
    public ResponseEntity<Object> findAll( String lang) {
        try {
            if (lang != null && lang.equals("en")) {

                return new ResponseEntity<>( faqRepository.findAllWithFilter_en(), HttpStatus.OK);
            }
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
            log.info(faq +"");
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();
            faq.setUser_id(user.getId());

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


        try {
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();

            Faq faq = new Faq();
            faq.setName( name);faq.setEmail(email);
            faq.setQuestion_en(questionEn);
            faq.setStatus(status);
            faq.setUser_id(user.getId());
            log.info(faq +"");
            return new ResponseEntity<>(faqRepository.save(faq), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),110), HttpStatus.BAD_REQUEST);

        }
    }
    @Override
    public ResponseEntity<Object> update(Faq faq) {
        try {
            log.info(faq+"");
           Faq  faq_tp = faqRepository.findById(faq.getId()).orElse(null);
           if (faq_tp!=null){


          faq_tp.setAnswer_ar(faq.getAnswer_ar());
            User user = userRepository.findById(faq_tp.getUser_id()).get();
            Notification notification = new Notification();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
               String questionAr = faq_tp.getQuestion_ar();
               String q = questionAr.length() > 20 ? questionAr.substring(20) : questionAr;

               notification.setTitle("بخصوص استفساركم: "+q);
            notification.setDescription(faq_tp.getAnswer_ar());
            notification.setUser_id(user.getId());
            notification.setStatus(3 + "");

            notification.setCreationDate(formatter.format(now));
            notification.setStatusReviewed("pending");
            notification.setType("3");
            notificationRepository.save(notification);

            fbNotificationService.sendNotification(user.getFirebase_token(),
                    notification.getTitle(), notification.getDescription(),"list",notification.getCreationDate(),"3","1","from_admin");
            return new ResponseEntity<>(faqRepository.save(faq_tp), HttpStatus.OK);
           }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),109), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(Constants.responseMessage("please check the faq id",109), HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<Object> updateFromAdmin(Faq faq) {

            try {
                log.info(faq +"");
                User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();
                faq.setUser_id(user.getId());

                return new ResponseEntity<>(faqRepository.save(faq), HttpStatus.OK);
            } catch (Exception exception) {
                exception.printStackTrace();
                return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),109), HttpStatus.BAD_REQUEST);

            }

    }

    @Override
    public ResponseEntity<Object> findAllSupport() {
        try {
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();
            if (user.getRole().equals("admin")){
               List<Faq> faqList= faqRepository.findAllSupport();
            return new ResponseEntity<>(faqList.isEmpty()?new ArrayList<>():faqList, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(faqRepository.findFaqByUser_idIs(user.getId()), HttpStatus.OK);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),111), HttpStatus.BAD_REQUEST);

        }
    }





}
