package com.application.masl7tak.Repository;

import com.application.masl7tak.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    @Query("SELECT i FROM Invitation i WHERE i.invitationToken = :invitationToken")
    Invitation findByInvitationToken(@Param("invitationToken") String invitationToken);

    @Query("SELECT COUNT(i) FROM Invitation i WHERE i.inviteeEmail = :inviteeEmail")
    long existsByInviteeEmail(@Param("inviteeEmail") String inviteeEmail);



}