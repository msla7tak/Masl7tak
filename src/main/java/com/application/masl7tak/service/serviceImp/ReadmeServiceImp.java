package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.Repository.ServicesRepository;
import com.application.masl7tak.Repository.ReadmeRepository;
import com.application.masl7tak.dto.ReadmeDTO;
import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.model.Readme;
import com.application.masl7tak.service.ReadmeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ReadmeServiceImp implements ReadmeService {
    private final ReadmeRepository readmeRepository;
    private final ServicesRepository servicesRepository;

    @Autowired
    public ReadmeServiceImp(ReadmeRepository readmeRepository,
                            ServicesRepository servicesRepository) {
        this.readmeRepository = readmeRepository;
        this.servicesRepository = servicesRepository;
    }

    @Override
    public ResponseEntity<List<ReadmeDTO>> findAll() {
        try {

            return new ResponseEntity<>(readmeRepository.findAllReadme(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ReadmeDTO> findById(Long readmeId) {
        try {

            return new ResponseEntity<>(readmeRepository.findReadmeById(readmeId), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ReadmeDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<ReadmeDTO> save(Readme readme) {
        try {
            ServicesDTO servicesDTO = servicesRepository.findBy_Id(readme.getServices().getId());
            if (servicesDTO.getReadme_num() < servicesDTO.getMax_usage()) {
                LocalDate today = LocalDate.now();
                readme.setDate(today);
                servicesRepository.readme_num(servicesDTO.getId());
                servicesRepository.isAvailable(servicesDTO.getId());

                return new ResponseEntity<>(readmeRepository.findReadmeById(readmeRepository.save(readme).getId()), HttpStatus.CREATED);
                //return new ResponseEntity<>(Constants.DATA_Inserted, HttpStatus.OK);
            } else
                return new ResponseEntity<>(new ReadmeDTO(), HttpStatus.BAD_REQUEST);


        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ReadmeDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        readmeRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<String> update(String comment, Float rate, Long readmeId) {
        try {
            comment = (comment.equals("")) ? null : comment;
            readmeRepository.update(comment, rate, readmeId);
            return new ResponseEntity<>(Constants.DATA_Inserted, HttpStatus.OK);
        } catch (Exception exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateInvoicePath(String path, String total_invoice, Long readmeId) {
        try {
            if (readmeRepository.findReadmeById(readmeId).getConfirm_invoice() == 2) {
                readmeRepository.coupons_invoice(0, "", readmeId);
            }
            path = (path.equals("")) ? null : path;
            readmeRepository.updateInvoicePath(path, total_invoice, readmeId);
            return new ResponseEntity<>(Constants.DATA_Inserted, HttpStatus.OK);
        } catch (Exception exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> findUserCoupons(Long userId) {
        try {

            return new ResponseEntity<>(readmeRepository.findUserCoupons(userId), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();

            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 109), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Object> findBusinessCoupons(Long businessId, String date) {
        try {

            return new ResponseEntity<>(readmeRepository.findBusinessCoupons(businessId, date), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 106), HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public ResponseEntity<Object> coupons_date(String scheduleDate, String scheduleTime, int confirmDate, Long readmeId) {
        try {
            readmeRepository.coupons_date(scheduleDate, scheduleTime, confirmDate, readmeId);

            return new ResponseEntity<Object>(readmeRepository.findReadmeById(readmeId), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 107), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<ReadmeDTO> coupons_invoice(int confirmInvoice, String reason, Long readmeId) {
        try {
            readmeRepository.coupons_invoice(confirmInvoice, reason, readmeId);

            return new ResponseEntity<ReadmeDTO>(readmeRepository.findReadmeById(readmeId), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ReadmeDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<ServicesDTO> most_redeemed(Long business_id) {
        List<Long> maxCouponUsage = readmeRepository.mostRedeemed(business_id);
//        log.info("test "+maxCouponUsage );

        List<ServicesDTO> maxCouponUsageMap = new ArrayList<>();
        for (Long couponId : maxCouponUsage) {

            ServicesDTO servicesDTO = servicesRepository.findBy_Id(couponId);
            log.info("test:" + servicesDTO);

            maxCouponUsageMap.add(servicesDTO);
        }

        return maxCouponUsageMap;
    }

    @Override
    public List<ServicesDTO> most_visited(Long business_id) {
        List<Long> maxCouponUsage = readmeRepository.most_visited(business_id);
//        log.info("test "+maxCouponUsage );

        List<ServicesDTO> maxCouponUsageMap = new ArrayList<>();
        for (Long couponId : maxCouponUsage) {

            ServicesDTO servicesDTO = servicesRepository.findBy_Id(couponId);
            log.info("test:" + servicesDTO);

            maxCouponUsageMap.add(servicesDTO);
        }

        return maxCouponUsageMap;
    }

    @Override
    public ResponseEntity<Object> findReadmeById(Long readmeId,Long businessId) {
        try {
            ReadmeDTO readmeDTO= readmeRepository.findReadme(readmeId);
            ServicesDTO servicesDTO= servicesRepository.findBy_Id(readmeDTO.getServices_id());
            if (servicesDTO.getBusiness().getId().equals(businessId))
            {
                return new ResponseEntity<>(readmeDTO, HttpStatus.OK);

            }
            else
                return new ResponseEntity<>(Constants.responseMessage("This coupon is not available in your store", 106), HttpStatus.BAD_REQUEST);


        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 106), HttpStatus.BAD_REQUEST);
        }
    }

    @Override

    public List<ServicesDTO> getMaxCouponUsage() {
        List<Long> maxCouponUsage = readmeRepository.findMaxServicesUsage();
//        log.info("test "+maxCouponUsage );

        List<ServicesDTO> maxCouponUsageMap = new ArrayList<>();
        for (Long couponId : maxCouponUsage) {

            ServicesDTO servicesDTO = servicesRepository.findBy_Id(couponId);
            log.info("test:" + servicesDTO);

            maxCouponUsageMap.add(servicesDTO);
        }

        return maxCouponUsageMap;
    }


}
