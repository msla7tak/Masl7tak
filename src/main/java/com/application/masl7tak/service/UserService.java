package com.application.masl7tak.service;

import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface UserService {
    ResponseEntity<Object> signUp(Map<String, String> requestMap);

    ResponseEntity<Object> login(Map<String, String> requestMap);

    ResponseEntity<List<UserDTO>> getAllUser(int offset);
    ResponseEntity<List<UserDTO>> lastRegisteredUsers();

    ResponseEntity<Object> changePassword(Map<String, String> requestmap);

    ResponseEntity<String> forgetPassword(Map<String, String> requestmap);

    ResponseEntity<Object> Social_login(Map<String, String> requestMap);

    ResponseEntity<SuccessDTO> updateImage(MultipartFile[] imagePath, Long id);

    ResponseEntity<SuccessDTO> updateProfile(Long id,MultipartFile[] image, String name, String contactNumber, String email, String password,String old_password, int carBrand, int carModel);

    ResponseEntity<SuccessDTO> deleteById(Long id);

    ResponseEntity<SuccessDTO> updatePoints(Integer point, Long id);

    ResponseEntity<UserDTO> profile(Long id);

    ResponseEntity<List<UserDTO>> getAll();

    ResponseEntity<Object> create_admin(Map<String, String> requestMap);
}




