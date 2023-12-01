package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.NotificationRepository;
import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.NotificationDTO;
import com.application.masl7tak.dto.UserDTO;
import com.application.masl7tak.model.Notification;
import com.application.masl7tak.model.User;
import com.application.masl7tak.service.NotificationService;
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
public class NotificationServiceImp implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FBNotificationService fbNotificationService;


    @Override
    public ResponseEntity<List<NotificationDTO>> findAll(Long userId) {
        try {
            List<NotificationDTO> notificationDTOList = notificationRepository.getAllNotification(userId);
            for (NotificationDTO notificationDTO:notificationDTOList) {
                notificationRepository.updateStatus(notificationDTO.getId(),"seen");
            }
            notificationDTOList.addAll(notificationRepository.getAllNotification(0L));
            return new ResponseEntity<>(notificationDTOList, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Notification> findById(Long id) {
        try {

            return new ResponseEntity<Notification>(notificationRepository.findById(id).orElse(null), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new Notification(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Notification> save(Notification notification) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            notification.setCreationDate(formatter.format(now));
            notification.setStatusReviewed("pending");
            User user = userRepository.findById(notification.getUser_id()).get();
            fbNotificationService.sendNotification(user.getFirebase_token(),
                    notification.getTitle(), notification.getDescription(),"list",notification.getCreationDate(),"3","1","from_admin");

            return new ResponseEntity<>(notificationRepository.save(notification), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Notification(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> create(Notification notification) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            notification.setCreationDate(formatter.format(now));
            List<UserDTO> userDTOS = userRepository.AllUsers();
            fbNotificationService.sendNotificationToAllUsers(notification);
            List<Notification> notifications = new ArrayList<>();
            for (UserDTO userDTO : userDTOS) {
                Notification notifi = new Notification();
                notifi.setTitle(notification.getTitle());
                notifi.setStatusReviewed("pending");
                notifi.setCreationDate(formatter.format(now));
                notifi.setDescription(notification.getDescription());
                notifi.setUser_id(userDTO.getId());
                notifications.add(notifi);
            }
            notificationRepository.saveAll(notifications);

            return new ResponseEntity<>(Constants.responseMessage("Done", 1007), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 1006), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Object> createBusiness(Notification notification) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            notification.setCreationDate(formatter.format(now));
            List<UserDTO> userDTOS = userRepository.AllBusiness();
          fbNotificationService.sendNotificationToAllBusiness( notification);

            List<Notification> notifications = new ArrayList<>();
            for (UserDTO userDTO : userDTOS) {
                Notification notifi = new Notification();
                notifi.setTitle(notification.getTitle());
                notifi.setStatusReviewed("pending");
                notifi.setCreationDate(formatter.format(now));
                notifi.setDescription(notification.getDescription());
                notifi.setUser_id(userDTO.getId());
                notifications.add(notifi);
            }
            notificationRepository.saveAll(notifications);

            return new ResponseEntity<>(Constants.responseMessage("Done", 1007), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 1006), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Object> unSeen(Long userId) {
        try {
            return new ResponseEntity<>(Constants.responseMessage(  notificationRepository.unSeen("pending",userId), 200), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 1008), HttpStatus.BAD_REQUEST);

        }
    }


}
