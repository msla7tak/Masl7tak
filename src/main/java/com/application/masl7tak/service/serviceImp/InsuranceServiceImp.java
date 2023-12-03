package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.InsuranceRepository;
import com.application.masl7tak.Repository.NotificationRepository;
import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.InsuranceDTO;
import com.application.masl7tak.model.Insurance;
import com.application.masl7tak.model.Notification;
import com.application.masl7tak.model.User;
import com.application.masl7tak.service.InsuranceService;
import com.application.masl7tak.utils.FBNotificationService;
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
public class InsuranceServiceImp implements InsuranceService {
    @Autowired
   private  InsuranceRepository insuranceRepository;
    @Autowired
   private  NotificationRepository notificationRepository;
    @Autowired
    private FBNotificationService fbNotificationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;


    @Override
    public ResponseEntity<List<InsuranceDTO>> findAll() {
        try {

            return new ResponseEntity<List<InsuranceDTO>>(insuranceRepository.findAllInsurance(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<InsuranceDTO> findById(Long id) {
        try {

            return new ResponseEntity<InsuranceDTO>(insuranceRepository.findInsuranceById(id), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new InsuranceDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<InsuranceDTO> save(Insurance insurance) {
        try {
            Insurance row= insuranceRepository.save(insurance);
           Notification notification= new Notification(row);
            notification.setType("5");
            notificationRepository.save(notification);
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();

            fbNotificationService.sendNotification(user.getFirebase_token(), notification.getTitle(), notification.getDescription(),
                    "insurance",notification.getCreationDate(),"5","1","Insurance");

            return new ResponseEntity<>(new InsuranceDTO(row), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new InsuranceDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        insuranceRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> AcceptOffer(String insuranceLogo, String insuranceContact,
                                              String insuranceType, String insurancePrice, String insurancePeriod,
                                              String commission, String insurancePriceAr, Long insuranceId) {
        try {
           insuranceRepository.AcceptOffer( insuranceLogo,  insuranceContact,  insuranceType,
                    insurancePrice,  insurancePeriod,  commission,insurancePriceAr,  insuranceId);
       Notification  notification=     new Notification(insuranceRepository.findById(insuranceId).orElseThrow(),"Reviewed");
            notification.setType("6");
                    notificationRepository.save(notification);

            User user = userRepository.findById(notification.getUser_id()).get();

            fbNotificationService.sendNotification(user.getFirebase_token(), notification.getTitle(), notification.getDescription()
                    ,"insurance","","6",notification.getCreationDate()+"","insurance_accept_offer");

            return new ResponseEntity<>(Constants.responseMessage(Constants.DATA_Inserted,104), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),104), HttpStatus.BAD_REQUEST);

        }}

    @Override
    public ResponseEntity<InsuranceDTO> updateInvoice(String invoiceId, Long id) {
        try {
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();

            insuranceRepository.updateInvoice(invoiceId,id,"3");
            InsuranceDTO  insurance= insuranceRepository.findInsuranceById(id);


            return new ResponseEntity<>(insurance, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new InsuranceDTO(), HttpStatus.BAD_REQUEST);
    }


}
