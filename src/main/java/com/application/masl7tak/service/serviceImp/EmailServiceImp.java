package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailServiceImp implements EmailService {
    @Autowired
    private final JavaMailSender mailSender;

    public EmailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendInvitationEmail(String recipientEmail, String invitationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Invitation to Join");
        message.setText("You have been invited to join our application. Please click the following link to accept the invitation: "
                + "http://example.com/invitations/" + invitationToken);

        mailSender.send(message);
    }
}
