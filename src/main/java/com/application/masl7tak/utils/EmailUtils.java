package com.application.masl7tak.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Slf4j
@Service
public class EmailUtils {

//    @Autowired
//    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dev.ahmedqotb@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        if (list != null && list.size() > 0)
            message.setCc(getCcArray(list));
//        emailSender.send(message);
    }


    private String[] getCcArray(List<String> ccList) {
        String[] cc = new String[ccList.size()];
        for (int i = 0; i < ccList.size(); i++) {
            cc[i] = ccList.get(i);
        }
        return cc;
    }
//    private void forgetMail(String to, String subject, String  password)throws MessagingException {
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper= new MimeMessageHelper(message,true);
//        helper.setFrom("dev.ahmedqotb@gmail.com");
//        helper.setTo(to);
//        helper.setSubject(subject);
//        String htmlMdg= "<p><b>Your Login details </b><br><b>Email: </b> \" + to + \" <br><b>Password: </b> \" + password + \"<br><a href=\\\"http://localhost:4200/\\\">Click here to login</a></p>";
//        message.setContent(htmlMdg,"text/html");
//        emailSender.send(message);
//    }
}
