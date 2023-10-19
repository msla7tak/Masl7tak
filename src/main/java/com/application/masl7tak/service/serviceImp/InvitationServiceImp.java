package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.InvitationRepository;
import com.application.masl7tak.Repository.ReplacementRepository;
import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.model.Invitation;
import com.application.masl7tak.model.User;
import com.application.masl7tak.service.EmailService;
import com.application.masl7tak.service.InvitationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
public class InvitationServiceImp implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplacementRepository replacementRepository;

    @Transactional
    @Override
    public ResponseEntity<String> sendInvitation(Long inviterId, String inviteeEmail) {
        // Get the inviter user from the database based on inviterId
        try {
            User inviter = userRepository.findById(inviterId)
                    .orElseThrow(() -> new IllegalArgumentException("Inviter not found"));

            if (!invitationRepository.existsByInviteeEmail(inviteeEmail)) {
                Invitation invitation = new Invitation();
                invitation.setInviter(inviter);
                invitation.setInviteeEmail(inviteeEmail);
                invitation.setInvitationToken(generateInvitationToken());

                // Send the invitation email using the emailService
                emailService.sendInvitationEmail(inviteeEmail, invitation.getInvitationToken());
                invitationRepository.save(invitation);
                ResponseEntity.ok("Invitation sent successfully.");
            } else
            {
                log.info(invitationRepository.existsByInviteeEmail(inviteeEmail).toString());
                return new ResponseEntity<>("Please check the Email existing.", HttpStatus.CONFLICT);}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Please check the Email.", HttpStatus.NOT_FOUND);
    }

    @Transactional
    @Override
    public ResponseEntity<String> acceptInvitation(String invitationToken) {
        if (!invitationToken.isEmpty()) {
            Invitation invitation = invitationRepository.findByInvitationToken(invitationToken);
            if (invitation != null && !invitation.isAccepted()) {
                invitation.setAccepted(true);
                invitationRepository.save(invitation);
                User inviter = invitation.getInviter();
                Integer point =replacementRepository.getReferenceById(1L).getPoint_for_invitation();
                userRepository.updateInvitationCode(invitation.getInviteeEmail(),invitationToken);
                userRepository.updatePoints(inviter.getPoints()+point,inviter.getId());
                return ResponseEntity.ok("Invitation accepted successfully.");
                // Update inviter's points in the points table
                // ...
            }
        }

        return new ResponseEntity<>("Please check the Token.", HttpStatus.NOT_FOUND);
    }

    public String generateInvitationToken() {
        UUID token = UUID.randomUUID();
        return token.toString();
    }
}
