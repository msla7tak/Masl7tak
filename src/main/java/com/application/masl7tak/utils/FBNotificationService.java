package com.application.masl7tak.utils;

import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.dto.UserDTO;
import com.application.masl7tak.model.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FBNotificationService {
    @Autowired
    private UserRepository deviceTokenRepository;


    public void sendNotification(String token, String title, String body) {
        Message message = Message.builder()
                .putData("title", title)
                .putData("body", body)
                .setToken(token)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }

    public void sendNotificationToAllUsers(String title, String body) {
        List<UserDTO> allTokens = deviceTokenRepository.AllUsersToking();

        for (UserDTO deviceToken : allTokens) {


            Message message = Message.builder()
                    .setToken(deviceToken.getFirebase_token())
                    .putData("title", title)
                    .putData("body", body)
                    .build();

            try {
                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println("Successfully sent message to device with token " + deviceToken.getFirebase_token() + ": " + response);
            } catch (Exception e) {
                System.err.println("Error sending message to device with token " + deviceToken.getFirebase_token() + ": " + e.getMessage());
            }
        }
    }

    public void sendNotificationToAllBusiness(String title, String body) {

        List<UserDTO> allTokens = deviceTokenRepository.AllBusinessToking();

        for (UserDTO deviceToken : allTokens) {
            Message message = Message.builder()
                    .putData("title", title)
                    .putData("body", body)
                    .setToken(deviceToken.getFirebase_token())
                    .build();

            try {
                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println("Successfully sent message to device with token " + deviceToken.getFirebase_token() + ": " + response);
            } catch (Exception e) {
                System.err.println("Error sending message to device with token " + deviceToken.getFirebase_token() + ": " + e.getMessage());
            }
        }
    }


}
