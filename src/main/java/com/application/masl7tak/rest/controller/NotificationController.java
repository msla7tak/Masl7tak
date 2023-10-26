package com.application.masl7tak.rest.controller;


import com.application.masl7tak.dto.NotificationDTO;
import com.application.masl7tak.model.Notification;
import com.application.masl7tak.rest.api.NotificationAPI;
import com.application.masl7tak.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    public class NotificationController implements NotificationAPI {
      private final  NotificationService notificationService;
    @Autowired

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @Override
    public ResponseEntity<List<NotificationDTO>> findAll(Long UserId) {
        return notificationService.findAll(UserId);
    }

    @Override
    public ResponseEntity<Notification> findById(@PathVariable Long id) {
        return notificationService.findById(id);
    }

    @Override
    public ResponseEntity<Notification>  save(@RequestBody Notification notification) {
        return notificationService.save(notification);
    }

    @Override
    public ResponseEntity<Object> create(Notification notification) {
        return notificationService.create(notification);
    }

    @Override
    public ResponseEntity<Object> createBusiness(Notification notification) {
        return notificationService.createBusiness(notification);
    }

    @Override
    public ResponseEntity<Notification> update(@RequestBody Notification notification, @PathVariable Long id) {
        notification.setId(id);
        return notificationService.save(notification);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        notificationService.deleteById(id);
    }
}
