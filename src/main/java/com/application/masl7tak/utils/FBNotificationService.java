package com.application.masl7tak.utils;

import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.dto.UserDTO;
import com.application.masl7tak.model.Notification;
import com.application.masl7tak.model.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FBNotificationService {
    @Autowired
    private UserRepository deviceTokenRepository;


    public void sendNotification(String token, String title, String body,String route,String creationDate,String type,String status,String type_text) {
        Message message = Message.builder()
                .putData("title", title)
                .putData("body", body)
                .putData("type", type)
                .putData("type_text", type_text)
                .putData("status",status)
                .putData("body", body)
                .putData("creation_date", creationDate)
                .putData("route", route)
                .setToken(token)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }

    public void sendNotificationToAllUsers(Notification notification) {
        List<UserDTO> allTokens = deviceTokenRepository.AllUsersToking();

        for (UserDTO deviceToken : allTokens) {
            if (deviceToken.getFirebase_token().isEmpty()){
                continue;
            }
            Message message = Message.builder()
                    .setToken(deviceToken.getFirebase_token())
                    .putData("title", notification.getTitle())
                    .putData("type", "1")
                    .putData("type_text", "all_user")
                    .putData("status","1")
                    .putData("title", notification.getTitle())
                    .putData("body", notification.getDescription())
                    .putData("creation_date", notification.getCreationDate())
                    .putData("route", "list")
                    .build();

            try {
                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println("Successfully sent message to device with token " + deviceToken.getFirebase_token() + ": " + response);
            } catch (Exception e) {
                System.err.println("Error sending message to device with token " + deviceToken.getFirebase_token() + ": " + e.getMessage());
            }
        }
    }

    public void sendNotificationToAllBusiness(Notification notification) {

        List<UserDTO> allTokens = deviceTokenRepository.AllBusinessToking();

        for (UserDTO deviceToken : allTokens) {
            if (deviceToken.getFirebase_token().isEmpty()){
                continue;
            }
            Message message = Message.builder()
                    .putData("title", notification.getTitle())
                    .putData("type", "2")
                    .putData("type_text", "all_business")
                    .putData("status","2")
                    .putData("body", notification.getDescription())
                    .putData("creation_date", notification.getCreationDate())
                    .putData("route", "list")
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
