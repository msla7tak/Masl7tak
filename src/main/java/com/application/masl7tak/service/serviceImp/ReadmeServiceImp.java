package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.*;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.CommentDTO;
import com.application.masl7tak.dto.ReadmeDTO;
import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.dto.UserDTO;
import com.application.masl7tak.model.*;
import com.application.masl7tak.service.ReadmeService;
import com.application.masl7tak.utils.FBNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
public class ReadmeServiceImp implements ReadmeService {
    @Autowired
    private ReadmeRepository readmeRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserPromoCodeRepository userPromoCodeRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private ReplacementRepository replacementRepository;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Autowired
    private FBNotificationService fbNotificationService;

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
    public ResponseEntity<Object> save(Readme readme) {
        try {
            ServicesDTO servicesDTO = servicesRepository.findBy_Id(readme.getServices().getId());
            readme.setBusiness_app_promo_code(servicesDTO.getBusiness_app_promo_code());
            PromoCode promoCode = promoCodeRepository.findByCode(readme.getPromo_code()).orElse(null);
            if (servicesDTO.getReadme_num() < servicesDTO.getMax_usage()) {
                LocalDate today = LocalDate.now();
                readme.setDate(today);
                servicesRepository.readme_num(servicesDTO.getId());
                servicesRepository.isAvailable(servicesDTO.getId());
                servicesRepository.reCountService(servicesDTO.getBrand_id(), servicesDTO.getId());
                if (promoCode != null) {
                    List<Long> allBusinessIds = promoCode.getAllBusinessIds();
                    if (allBusinessIds.contains(servicesDTO.getBusiness().getId())) {
                        if (promoCode.getReadme_num() < promoCode.getMax_usage()) {
                            log.info(userPromoCodeRepository.findUserById(promoCode.getId(), readme.getUser().getId())+ "");
                            if (userPromoCodeRepository.findUserById(promoCode.getId(), readme.getUser().getId()).isEmpty()) {
                                promoCodeRepository.readme_num(promoCode.getId());
                                promoCodeRepository.isAvailable(promoCode.getId());
                                readme.setPromo_code_discount(promoCode.getDiscountValue());
                                userPromoCodeRepository.save(new UserPromoCode( readme.getUser().getId(),promoCode.getId()));

                            }
                        }
                    }
                } else
                    readme.setPromo_code_discount(0.0);

                return new ResponseEntity<>(readmeRepository.findReadmeById(readmeRepository.save(readme).getId()), HttpStatus.CREATED);
                //return new ResponseEntity<>(Constants.DATA_Inserted, HttpStatus.OK);
            } else
                return new ResponseEntity<>(Constants.responseMessage("Coupon expired ", 144), HttpStatus.BAD_REQUEST);


        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 150), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void deleteById(Long id) {
        readmeRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<String> update(String comment, Float rate, Long readmeId) {
        try {
            Readme readme = readmeRepository.findById(readmeId).get();
            servicesRepository.comments_numCount(readme.getServices().getId());
            comment = (comment.equals("")) ? null : comment;
            readmeRepository.update(comment, rate, readmeId);
            servicesRepository.updateRate(rate, readme.getServices().getId());
            businessRepository.updateRate(rate, readme.getBusiness_id());
            return new ResponseEntity<>(Constants.DATA_Inserted, HttpStatus.OK);
        } catch (Exception exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateInvoicePath(String path, String total_invoice, Long readmeId) {
        try {
           ReadmeDTO readme= readmeRepository.findReadmeById(readmeId);
           Long  getBusiness  = servicesRepository.findBy_Id(readme.getServices_id()).getBusiness().getId();
            User userBusiness = userRepository.findByBusiness(getBusiness) ;
            if (readmeRepository.findReadmeById(readmeId).getConfirm_invoice() == 2) {
                readmeRepository.coupons_invoice(0, "", readmeId);
            }

            path = (path.equals("")) ? null : path;
            readmeRepository.updateInvoicePath(path, total_invoice, readmeId);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();
            Notification notification = new Notification();

            notification.setTitle("تم اضافة فاتورة");
            notification.setDescription("برجاء مراجعة الفاتوره المرفقة");
            notification.setUser_id(userBusiness.getId());
            notification.setStatus(userBusiness.getBusiness_id()+"");
            notification.setCreationDate(formatter.format(now));
            notification.setStatusReviewed("pending");
            notification.setType("7");
            notificationRepository.save(notification);

            fbNotificationService.sendNotification(userBusiness.getFirebase_token(), notification.getTitle(), notification.getDescription(),
                    "list", notification.getCreationDate(), "7", userBusiness.getBusiness_id()+"", "invoice");
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
            if (date.equals("")) {
                return new ResponseEntity<>(readmeRepository.findBusinessCouponsIDs(businessId), HttpStatus.OK);
            }
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
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            ReadmeDTO readme= readmeRepository.findReadmeById(readmeId);

            User user = userRepository.findById(readme.getUser_id()).get();

            Notification notification = new Notification();

            notification.setTitle("تم تحديث طلبك");
            notification.setDescription("تم تحديث حالةالطلب بنجاح");
            notification.setUser_id(user.getId());
            notification.setStatus(confirmDate + "");
            notification.setCreationDate(confirmDate + "");
            notification.setCreationDate(formatter.format(now));
            notification.setStatusReviewed("pending");
            notification.setType("4");
            notificationRepository.save(notification);

            fbNotificationService.sendNotification(user.getFirebase_token(), notification.getTitle(), notification.getDescription(),
                    "list", notification.getCreationDate(), "4", confirmDate + "", "confirmDate");

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
            ReadmeDTO readmeDTO = readmeRepository.findReadmeById(readmeId);
            if (confirmInvoice == 1) {
                User user = userRepository.findById(readmeDTO.getUser_id()).get();
                Replacement replacement = replacementRepository.getReferenceById(1L);
                Integer point = replacement.getRedeemed_points();
                Integer LE = replacement.getPoint_valueLE();
                 Integer total_point= (Integer.parseInt(readmeDTO.getTotal_invoice() )/ LE) * point;
                userRepository.updatePoints(user.getPoints() + total_point, user.getId());
            }
            return new ResponseEntity<ReadmeDTO>(readmeDTO, HttpStatus.OK);

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
    public ResponseEntity<Object> findReadmeById(Long readmeId, Long businessId) {
        try {
            ReadmeDTO readmeDTO = readmeRepository.findReadme(readmeId);
            ServicesDTO servicesDTO = servicesRepository.findBy_Id(readmeDTO.getServices_id());
            if (servicesDTO.getBusiness().getId().equals(businessId)) {
                return new ResponseEntity<>(readmeDTO, HttpStatus.OK);

            } else
                return new ResponseEntity<>(Constants.responseMessage("This coupon is not available in your store", 106), HttpStatus.BAD_REQUEST);


        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 106), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> geComment() {
        try {
          List<CommentDTO>  comments = readmeRepository.getComment();
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 106), HttpStatus.BAD_REQUEST);
        }
    }

    @Override

    public List<ServicesDTO> getMaxCouponUsage() {
        LocalDate currentDate = LocalDate.now();

        List<Long> maxCouponUsage = readmeRepository.findMaxServicesUsage(currentDate);
//        log.info("test "+maxCouponUsage );
        List<ServicesDTO> maxCouponUsageMap = new ArrayList<>();
        for (Long couponId : maxCouponUsage) {
            try {

                ServicesDTO servicesDTO = servicesRepository.findBy_Id_date(couponId)
                        .orElseThrow(() -> new NoSuchElementException("Coupon not found"));
                maxCouponUsageMap.add(servicesDTO);
            } catch (NoSuchElementException e) {
                // Handle the exception or log a message
                System.out.println("Coupon not found for ID: " + couponId);
            }
        }

        return maxCouponUsageMap.isEmpty()?new ArrayList<>():maxCouponUsageMap;
    }


}
