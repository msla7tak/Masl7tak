package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.NotificationDTO;
import com.application.masl7tak.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT new com.application.masl7tak.dto.NotificationDTO(N.id, N.title, N.creationDate, N.description, N.statusReviewed,N.insurance.id)" +
            "FROM Notification N WHERE N.user_id=:userId")

    List<NotificationDTO> getAllNotification(Long userId);

}
