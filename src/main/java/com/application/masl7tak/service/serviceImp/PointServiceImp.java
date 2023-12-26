package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.*;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.model.*;
import com.application.masl7tak.service.AdminService;
import com.application.masl7tak.service.PointService;
import com.application.masl7tak.utils.FBNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PointServiceImp implements PointService {
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private  NotificationRepository notificationRepository;
    @Autowired
    private FBNotificationService fbNotificationService;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplacementRepository replacementRepository;
    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public ResponseEntity<List<Point>> findAll() {
        try {

            return new ResponseEntity<>(pointRepository.findAll(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Point> findById(Long id) {
        try {

            return new ResponseEntity<>(pointRepository.findById(id).get(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new Point(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<Object> save(Point point) {
        try {
            User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();
            Replacement replacement = replacementRepository.findById(1L).orElseThrow();

            if (user.getPoints() >= replacement.getMin_no_of_points_to_change()) {
                userRepository.updatePoints(0, user.getId());
                return new ResponseEntity<>(pointRepository.save(point), HttpStatus.OK);
            }
            return new ResponseEntity<>(Constants.responseMessage("Can't exchange you point yet", 106), HttpStatus.BAD_REQUEST);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 107), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Object> update(Point point) {
        try {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            User user = userRepository.findById(point.getUser_id()).orElseThrow();
            Notification notification = new Notification();

            notification.setTitle("حصلت علي كود خصم "+point.getPromo_code());
            notification.setDescription("يمكنك الذهاب لصفحة النقاط لاستخدامها");
            notification.setUser_id(user.getId());
            notification.setStatus(8+"");
            notification.setCreationDate(formatter.format(now));
            notification.setStatusReviewed("pending");
            notification.setType("8");
            notificationRepository.save(notification);

            fbNotificationService.sendNotification(user.getFirebase_token(), notification.getTitle(), notification.getDescription(),
                    "point", notification.getCreationDate(), "8", 8+"", "point");
            return new ResponseEntity<>( pointRepository.save(point), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();

            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 107), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void deleteById(Long id) {
        pointRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<Point>> findRequestsByUserId(Long userId) {
        try {
            return new ResponseEntity<List<Point>>(pointRepository.findRequestsByUserId(userId), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


}
