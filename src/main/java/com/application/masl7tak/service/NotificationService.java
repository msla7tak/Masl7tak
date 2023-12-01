package com.application.masl7tak.service;

import com.application.masl7tak.dto.NotificationDTO;
import com.application.masl7tak.model.Notification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationService {
    ResponseEntity<List<NotificationDTO>> findAll(Long userId);

    ResponseEntity<Notification> findById(Long id);

    ResponseEntity<Notification> save(Notification notification);

    void deleteById(Long id);

    ResponseEntity<Object> create(Notification notification);

    ResponseEntity<Object> createBusiness(Notification notification);

    ResponseEntity<Object> unSeen(Long userId);
}
