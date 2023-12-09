package com.application.masl7tak.rest.controller;


import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.User;
import com.application.masl7tak.rest.api.UserAPI;


import com.application.masl7tak.service.InvitationService;
import com.application.masl7tak.service.UserService;
import com.application.masl7tak.utils.Utils;
import com.application.masl7tak.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController implements UserAPI {
    private final UserService userService;

    private final InvitationService invitationService;

    public UserController(UserService userService, InvitationService invitationService) {
        this.userService = userService;
        this.invitationService = invitationService;
    }


    @Override
    public ResponseEntity<Object> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new UserDTO(Constants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return new ResponseEntity<>(new UserDTO(Constants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> Social_login(Map<String, String> requestMap) {

        return userService.Social_login(requestMap);



}
    @Override
    public ResponseEntity<List<UserDTO>> getAllUser() {
        try {
//            return  ResponseEntity.ok(userService.getAllUser());
            return userService.getAllUser();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<UserDTO>> getAll() {
        try {
            return userService.getAll();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserDTO>> lastRegisteredUsers() {
        try {
            return userService.lastRegisteredUsers();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Object> changePassword(Map<String, String> requestmap) {
        try {
            return userService.changePassword(requestmap);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),105), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<String> forgetPassword(Map<String, String> requestmap) {
        try {
            return userService.forgetPassword(requestmap);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<SuccessDTO> updateImage(MultipartFile[] image_path, Long id) {
        return userService.updateImage(image_path,id);
    }

    @Override
    public ResponseEntity<UserDTO> profile(Long id) {
        return userService.profile(id);
    }

    @Override
    public ResponseEntity<SuccessDTO> updatePoints(Integer point, Long id) {
        return userService.updatePoints(point,id);    }

    @Override
    public ResponseEntity<SuccessDTO> updateProfile(Long id,MultipartFile[] image, String name, String contactNumber, String email, String password,String old_password, int carBrand, int carModel) {
        return userService.updateProfile( id,image,name,contactNumber,email,password,old_password,carBrand,carModel);
    }

    @Override
    public ResponseEntity<SuccessDTO> deleteById(Long id) {
        return userService.deleteById(id);
    }



    @Override
    public ResponseEntity<Object> sendInvitation(
            @PathVariable Long inviterId,
            @RequestParam String inviteeEmail) {
        return   invitationService.sendInvitation(inviterId, inviteeEmail);

    }

    @Override
    public ResponseEntity<String> acceptInvitation(@PathVariable String invitationToken) {
       return invitationService.acceptInvitation(invitationToken);

    }
}
