package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.NotificationDTO;
import com.application.masl7tak.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT new com.application.masl7tak.dto.NotificationDTO(N.id, N.title, N.creationDate, N.description, N.statusReviewed,N.insurance.id,N.status,N.type)" +
            "FROM Notification N WHERE N.user_id=:userId")

    List<NotificationDTO> getAllNotification(Long userId);
    @Query("SELECT COUNT(S.id) FROM Notification S where S.statusReviewed= :pending and S.user_id= :id")
    int unSeen(String pending,Long id);
    @Modifying
    @Query("update Notification b set b.statusReviewed = :seen  where b.id = :id")
    void updateStatus(Long id, String seen);

}
