package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.dto.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RequestMapping(path = "/api/")
public interface UserAPI {

    @PostMapping(path = "public/user/signup")
    public ResponseEntity<Object> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "public/user/login")
    public ResponseEntity<Object> login(@RequestBody(required = true) Map<String, String> requestMap);
    @PostMapping(path = "public/user/Social_login")
    public ResponseEntity<Object> Social_login (@RequestHeader Map<String, String> requestMap);

    @GetMapping(path = "admin/user/alluser")
    public ResponseEntity<List<UserDTO>> getAllUser();
    @GetMapping(path = "admin/user/all")
    public ResponseEntity<List<UserDTO>> getAll();
    @GetMapping(path = "admin/user/lastRegisteredUsers")
    public ResponseEntity<List<UserDTO>> lastRegisteredUsers();

    @PostMapping(path = "user/user/changepassword")
    public ResponseEntity<Object> changePassword(@RequestBody Map<String, String> requestmap);


    @PostMapping(path = "user/user/forgetpassword")
    public ResponseEntity<String> forgetPassword(@RequestBody Map<String, String> requestmap);

    @PutMapping("user/user/image_path")
    public ResponseEntity<SuccessDTO> updateImage(
                                         @RequestParam(name = "image") MultipartFile[] image_path,
                                         @RequestParam(name = "id" ) Long id);
    @GetMapping("user/user/profile")
    public ResponseEntity<UserDTO> profile(@RequestParam(name = "id" ) Long id);
    @PutMapping("user/user/point")
    public ResponseEntity<SuccessDTO> updatePoints(
                                         @RequestParam(name = "point") Integer point,
                                         @RequestParam(name = "id" ) Long id);

    @PutMapping("user/user/updateProfile")
    public ResponseEntity<SuccessDTO> updateProfile(  @RequestParam(name = "id" ) Long id,
            @RequestParam(name = "image", required = false) MultipartFile[] image,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "contact_number", required = false) String contactNumber,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "old_password", required = false) String old_password,
            @RequestParam(name = "car_brand", required = false) int carBrand,
            @RequestParam(name = "car_model", required = false) int carModel);

    @DeleteMapping("user/user/{id}")
    public ResponseEntity<SuccessDTO> deleteById(@PathVariable Long id);
    @PostMapping("user/user/invitations")
    public ResponseEntity<Object> sendInvitation(
            @RequestParam Long inviterId,
            @RequestParam String inviteeEmail);

    @GetMapping("user/user/invitations")
    public ResponseEntity<String> acceptInvitation(@RequestParam String invitationToken);
}
