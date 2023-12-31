package com.application.masl7tak.service;

import org.springframework.http.ResponseEntity;

public interface InvitationService {



    public  ResponseEntity<Object> sendInvitation(Long inviterId, String inviteeEmail);

    public ResponseEntity<String> acceptInvitation(String invitationToken);


    public String generateInvitationToken() ;
}
