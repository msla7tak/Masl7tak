package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.ReplacementRepository;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.configs.JwtService;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.User;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.Repository.UserRepository;
import com.application.masl7tak.rest.controller.AmazonS3Controller;
import com.application.masl7tak.service.UserService;
import com.application.masl7tak.utils.Utils;
import com.application.masl7tak.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private AmazonS3Controller amazonS3Controller;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ReplacementRepository replacementRepository;


    private boolean validateSignupMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("contact_number")
                && requestMap.containsKey("email") && requestMap.containsKey("password")
                && requestMap.containsKey("car_model") && requestMap.containsKey("car_brand");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contact_number"));
        user.setEmail(requestMap.get("email"));
        user.setFirebase_token(requestMap.get("firebase_token"));
        user.setCarModel(Integer.parseInt(requestMap.get("car_model")));
        user.setCarBrand(Integer.parseInt(requestMap.get("car_brand")));
        user.setPassword(passwordEncoder.encode(requestMap.get("password")));
        user.setStatus("active");
        user.setRole("user");
        user.setInvitation_code(generateInvitationToken());
        user.setInviter_code(requestMap.get("invitation_code"));
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(dateFormat);
        user.setCreationDate(formattedDate);
        return user;

    }

    @Transactional
    @Override
    public ResponseEntity<List<UserDTO>> getAllUser() {
        try {
            if (jwtAuthFilter.isAdmin())
                return new ResponseEntity<>(userRepository.getAllUser(), HttpStatus.OK);
            else
//                log.info(""+jwtAuthFilter.isAdmin());
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserDTO>> lastRegisteredUsers() {
        try {
            if (jwtAuthFilter.isAdmin())

                return new ResponseEntity<>(userRepository.lastRegisteredUsers(PageRequest.of(0, 10)), HttpStatus.OK);
            else
//                log.info(""+jwtAuthFilter.isAdmin());
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<Object> signUp(Map<String, String> requestMap) {
//        log.info("Inside signup{}", requestMap);
        try {

            if (validateSignupMap(requestMap)) {

                if (Objects.isNull(userRepository.findDtoByEmail(requestMap.get("email")))) {
                    String jwtToken = jwtService.generateToken(userRepository.save(getUserFromMap(requestMap)));
                    UserDTO userDTO = userRepository.findDtoByEmail(requestMap.get("email"));

                    userDTO.setToken(jwtToken);
                        if (requestMap.containsKey("firebase_token")){
                            if (!requestMap.get("firebase_token").isEmpty()){


                        User inviter = userRepository.findByInvitationCode(requestMap.get("invitation_code"));
                        Integer point = replacementRepository.getReferenceById(1L).getPoint_for_invitation();
                        userRepository.updatePoints((inviter.getPoints() + point), inviter.getId());

                            }
                        }
                    userRepository.updatePoints((replacementRepository.getReferenceById(1L).getPoint_for_registration()), userDTO.getId());

                    return new ResponseEntity<>(userDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(Constants.responseMessage("Email  already exits", 101), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(Constants.responseMessage(Constants.INVALID_DATA, 102), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public String generateInvitationToken() {
        UUID token = UUID.randomUUID();
        return token.toString().substring(0, 8);
    }

    @Override
    public ResponseEntity<Object> login(Map<String, String> requestMap) {

        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));

            if (auth.isAuthenticated()) {

                UserDTO userDTO = userRepository.findDtoByEmail(requestMap.get("email"));
                if (requestMap.get("firebase_token") != null || requestMap.get("firebase_token") != "") {
                    userRepository.updateFirebase(userDTO.getId(), requestMap.get("firebase_token"));
                }
                //          if (user.getStatus().equalsIgnoreCase("true")) {
//                    log.info("Inside login");
                var jwtToken = jwtService.generateToken(userRepository.findByEmail(requestMap.get("email")));
                userDTO.setToken(jwtToken);

                return new ResponseEntity<>(userDTO, HttpStatus.OK);
//                } else {
//                    return Utils.getResponseEntity("Wait for admin approval.", HttpStatus.BAD_REQUEST);
//
//                }
            } else
                return new ResponseEntity<>(Constants.responseMessage("Wrong credentials", 103), HttpStatus.BAD_REQUEST);


        } catch (Exception ex) {

            return new ResponseEntity<>(Constants.responseMessage(ex.getMessage(), 103), HttpStatus.BAD_REQUEST);

        }


    }

    @Transactional
    @Override
    public ResponseEntity<Object> changePassword(Map<String, String> requestmap) {
        try {
            log.info("currentUser: " + jwtAuthFilter.getCurrentUser());
            User userOb = userRepository.findByEmail(jwtAuthFilter.getCurrentUser());
            if (userOb != null) {

                if (passwordEncoder.matches(requestmap.get("old_password"), userOb.getPassword())) {
                    userOb.setPassword(passwordEncoder.encode(requestmap.get("new_password")));
                    userRepository.save(userOb);
                    return new ResponseEntity<>(Constants.responseMessage("Password Updated Successfully", 110), HttpStatus.BAD_REQUEST);

                }


            }
            return new ResponseEntity<>(Constants.responseMessage("Incorrect Old Password", 110), HttpStatus.BAD_REQUEST);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 105), HttpStatus.BAD_REQUEST);

        }

    }

    @Transactional
    @Override
    public ResponseEntity<String> forgetPassword(Map<String, String> requestmap) {
        try {
            User user = userRepository.findByEmail(requestmap.get("email"));
            if (user != null && Strings.isNotEmpty(user.getEmail())) {
//                emailUtils.forgetMail( user.getEmail(),  "Credentials by Masl7tak Management System",   user.getPassword());

                return Utils.getResponseEntity("Check your email for Credentials", HttpStatus.OK);
            }
            return Utils.getResponseEntity("Incorrect email", HttpStatus.BAD_REQUEST);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> Social_login(Map<String, String> requestMap) {
        try {
            User user = null;

            if (requestMap.containsKey("facebook_id")) {
                String email = requestMap.get("email");

                user = userRepository.findUserByEmail(email).orElse(null);

            } else if (requestMap.containsKey("gmail_id")) {
                String email = requestMap.get("email");

                user = userRepository.findUserByEmail(email).orElse(null);

            }

            if (user != null) {

                UserDTO userDTO = userRepository.findDtoByEmail(user.getEmail());
                if (requestMap.containsKey("firebase_token")){
                    if (!requestMap.get("firebase_token").isEmpty()) {

                        userRepository.updateFirebase(userDTO.getId(), requestMap.get("firebase_token"));
                        userDTO.setFirebase_token(requestMap.get("firebase_token"));
                    }
                }
                String jwtToken = jwtService.generateToken(user);
                userDTO.setToken(jwtToken);
                return new ResponseEntity<>(userDTO,
                        HttpStatus.OK);

            }

            user = new User();

            if (requestMap.containsKey("email")) {
                String email = requestMap.get("email");

            }
            log.info(requestMap.containsKey("email")+": "+ requestMap.get("email"));
            String email = requestMap.get("email");
            String[] list = email.split("@");
            user.setName(list[0]);
            user.setEmail(email);
            user.setCarModel(0);
            user.setCarBrand(0);

            user.setPassword(passwordEncoder.encode( email));
            user.setStatus("active");
            user.setRole("user");
            user.setInvitation_code(generateInvitationToken());
            user.setInviter_code("");
            if (requestMap.containsKey("firebase_token")){
                if (!requestMap.get("firebase_token").isEmpty()){
                    user.setFirebase_token(requestMap.get("firebase_token"));

                }
            }

            LocalDate today = LocalDate.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(dateFormat);
            user.setCreationDate(formattedDate);

            user.setGmail_id(requestMap.get("facebook_id"));
            user.setGmail_id(requestMap.get("gmail_id"));
            userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);


            userRepository.updatePoints(replacementRepository.getReferenceById(1L).getPoint_for_registration(), user.getId());
            UserDTO userDTO = userRepository.findDtoByEmail(user.getEmail());
            userDTO.setToken(jwtToken);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("{}", ex);
            return new ResponseEntity<>(Constants.responseMessage(ex.getMessage(), 105), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<SuccessDTO> updateImage(MultipartFile[] imagePath, Long id) {
        try {
            String image = amazonS3Controller.uploadFiles(imagePath);

            userRepository
                    .updateImage(image, id);
            log.info(image);
            return new ResponseEntity<>(new SuccessDTO(
                    id
                    , Constants.DATA_Inserted), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new SuccessDTO(
                0L
                , Constants.INVALID_DATA), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<SuccessDTO> updateProfile(Long id, MultipartFile[] image, String name, String contactNumber,
                                                    String email, String password, String old_password, int carBrand, int carModel) {
        try {
            User userOb = userRepository.findByEmail(jwtAuthFilter.getCurrentUser());
            int passwordChanged = 0;

            if ((password != null) && password.length() >= 6) {
                passwordChanged = 1;
                if (passwordEncoder.matches(old_password, userOb.getPassword())) {
                    userOb.setPassword(passwordEncoder.encode(password));
                    userRepository.save(userOb);
                    passwordChanged = 2;
                }
            }
//            else {
//                return new ResponseEntity<>(new SuccessDTO(
//                        0L
//                        , "Password must be more than six letters"), HttpStatus.UNAUTHORIZED);
//            }
            String image_path = null;
            if (image != null)
                image_path = amazonS3Controller.uploadFiles(image);

            userRepository.updateProfile(id, image_path, name, contactNumber, email, carBrand, carModel);
            if (passwordChanged == 0 || passwordChanged == 2) {
                return new ResponseEntity<>(new SuccessDTO(userOb.getId(), Constants.DATA_Inserted), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new SuccessDTO(userOb.getId(), Constants.WRONG_PASSWORD), HttpStatus.BAD_REQUEST);

            }

        } catch (Exception exception) {

            exception.printStackTrace();
        }

        return new ResponseEntity<>(new SuccessDTO(
                0L
                , Constants.INVALID_DATA), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<SuccessDTO> deleteById(Long id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(new SuccessDTO(id, "Account has been deleted as per your request"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new SuccessDTO(
                    0L
                    , Constants.INVALID_DATA), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<SuccessDTO> updatePoints(Integer point, Long id) {
        try {
            Integer points = userRepository.getPoints(id) + point;
            userRepository.updatePoints(points, id);
            return new ResponseEntity<>(new SuccessDTO(
                    id
                    , Constants.DATA_Inserted), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new SuccessDTO(
                0L
                , Constants.INVALID_DATA), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<UserDTO> profile(Long id) {
        try {

            UserDTO userDTO = userRepository.findDtoById(id);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);


        } catch (Exception ex) {

            return new ResponseEntity<>(new UserDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<List<UserDTO>> getAll() {
        try {
            List<UserDTO> userDTO = userRepository.AllUsers();

            return new ResponseEntity<>(userDTO, HttpStatus.OK);


        } catch (Exception ex) {

            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        }
    }

}

