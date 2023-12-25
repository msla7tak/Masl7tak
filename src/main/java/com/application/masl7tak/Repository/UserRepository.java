package com.application.masl7tak.Repository;

import com.application.masl7tak.model.User;
import com.application.masl7tak.dto.UserDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

//DAO means Data access object
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT COUNT(U.id) FROM User U")
    int countUsers();

    @Query ( "select U from User U where U.email=:email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT new com.application.masl7tak.dto.UserDTO(U.id, U.business_id, U.image, U.name,U.contactNumber, U.email," +
            "   U.role, U.status, U.points, U.carBrand, U.carModel," +
            "  U.facebook_id, U.gmail_id, U.invitation_code)from User U where U.email=:email" )
    UserDTO findDtoByEmail(@Param("email") String email);
    @Query("select U from User U where U.email=:email")
    User findByEmail(@Param("email") String email);
    @Query("select U.role from User U where U.email=:email")
    String findRoleByEmail(@Param("email") String email);
    @Query( "SELECT new com.application.masl7tak.dto.UserDTO(U.id, U.business_id, U.image, U.name,U.contactNumber, U.email," +
            "                   U.role, U.status, U.points, U.carBrand, U.carModel," +
            "                   U.facebook_id, U.gmail_id, U.invitation_code) FROM  User U ")
    List<UserDTO> getAllUser();
    @Query( "select U from User U where U.facebook_id=:facebook_id")
    User findByFacebookId(@Param("facebook_id") String facebookId);
    @Query( "select U from User U where U.gmail_id=:gmail_id")
    User findByGmailId(@Param("gmail_id") String gmailId);

    @Modifying
    @Query("update User u set u.image = :image where u.id = :id")
    void updateImage(@Param("image") String image,@Param("id") Long id);
//    (:productId is null OR P.id = :productId)
@Modifying
@Query("UPDATE User u SET " +
        "u.image = COALESCE(:imagePath, u.image), " +
        "u.name = COALESCE(:name, u.name), " +
        "u.contactNumber = COALESCE(:contactNumber, u.contactNumber), " +
        "u.email = COALESCE(:email, u.email), " +
        "u.carBrand = COALESCE(:carBrand, u.carBrand), " +
        "u.carModel = COALESCE(:carModel, u.carModel) " +
        "WHERE u.id = :id")
void updateProfile(@Param("id") Long id, @Param("imagePath") String imagePath, @Param("name") String name,
                   @Param("contactNumber") String contactNumber, @Param("email") String email,
                   @Param("carBrand") Integer carBrand, @Param("carModel") Integer carModel);

    @Modifying
    @Query("UPDATE User u SET u.role = 'business', u.business_id =:id" +
            " WHERE u.email = :currentUser")
    void updateRoleByEmail(String currentUser, Long id);

    @Modifying
    @Query("UPDATE User u SET u.points =:point" +
            " WHERE u.id = :id")
    void updatePoints(Integer point, Long id);
    @Query("select U.points from User U where U.id=:id")
    Integer getPoints(Long id);
    @Query("SELECT new com.application.masl7tak.dto.UserDTO( U.id, U.business_id, U.image, U.name,U.contactNumber, U.email," +
            "  U.role, U.status, U.points, U.carBrand, U.carModel," +
            "  U.facebook_id, U.gmail_id, U.invitation_code )from User U where U.id=:id")
    UserDTO findDtoById(Long id);

    @Modifying
    @Query("UPDATE User u SET u.invitation_code = :code WHERE u.email = :inviteeEmail")
    void updateInvitationCode(String inviteeEmail, String code);

    @Query("SELECT new com.application.masl7tak.dto.UserDTO(U.id, U.business_id, U.image, U.name, U.contactNumber, U.email," +
            "U.role, U.status, U.points, U.carBrand, U.carModel," +
            "U.facebook_id, U.gmail_id, U.invitation_code) " +
            "FROM User U " +
            "ORDER BY U.id DESC") // Order by id in descending order to get the latest records first
    List<UserDTO> lastRegisteredUsers(PageRequest pageable); // Use Pageable to limit the result to 5 records
    @Query("select U from User U where U.invitation_code=:invitationCode")

    User findByInvitationCode(String invitationCode);
    @Query("SELECT new com.application.masl7tak.dto.UserDTO(U.id,U.name) " +
            "FROM User U " ) // Order by id in descending order to get the latest records first
    List<UserDTO> AllUsers();
    @Query("SELECT new com.application.masl7tak.dto.UserDTO(U.firebase_token,U.id) " +
            "FROM User U where U.firebase_token!=null " ) // Order by id in descending order to get the latest records first
    List<UserDTO> AllUsersToking();
    @Query("SELECT new com.application.masl7tak.dto.UserDTO(U.id, U.name) " +
            "FROM User U " +
            "WHERE U.business_id IS NOT NULL")
    List<UserDTO> AllBusiness();
    @Query("SELECT new com.application.masl7tak.dto.UserDTO( U.firebase_token,U.id) " +
            "FROM User U " +
            "WHERE U.business_id IS NOT NULL and U.firebase_token!=null")
    List<UserDTO> AllBusinessToking();
    @Modifying
    @Query("UPDATE User u SET u.firebase_token = :firebaseToken WHERE u.id = :id")
    void updateFirebase(Long id, String firebaseToken);
    @Query("select U from User U where U.business_id= :getBusiness")
    User findByBusiness(Long getBusiness);

    @Modifying
    @Query("UPDATE User u SET u.business_id = null WHERE u.business_id = :id")
    void deleteID(Long id);


}
