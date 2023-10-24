package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.InsuranceRepository;
import com.application.masl7tak.Repository.NotificationRepository;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.InsuranceDTO;
import com.application.masl7tak.model.Insurance;
import com.application.masl7tak.model.Notification;
import com.application.masl7tak.service.InsuranceService;
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
   private final InsuranceRepository insuranceRepository;
   private final NotificationRepository notificationRepository;
    @Autowired
    public InsuranceServiceImp(InsuranceRepository insuranceRepository, NotificationRepository notificationRepository) {
        this.insuranceRepository = insuranceRepository;
        this.notificationRepository = notificationRepository;
    }



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
            notificationRepository.save(new Notification(row));
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

                    notificationRepository.save(new Notification(insuranceRepository.findById(insuranceId).orElseThrow(),"Reviewed"));
            return new ResponseEntity<>(Constants.responseMessage(Constants.DATA_Inserted,104), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),104), HttpStatus.BAD_REQUEST);

        }}


}
