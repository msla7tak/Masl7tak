package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.ReadmeDTO;
import com.application.masl7tak.model.Readme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReadmeRepository extends JpaRepository<Readme, Long> {

    @Query("SELECT c FROM Readme c WHERE c.user.id = :userId AND c.services.id = :couponsId")
    List<Readme> findByUserIdAndServicesId(@Param("userId") Long userId, @Param("couponsId") Long couponsId);

    @Query("SELECT c.services.id FROM Readme c GROUP BY c.services.id ORDER BY COUNT(c) DESC")
    List<Long> findMaxServicesUsage();
    @Query("SELECT c.services.id FROM Readme c WHERE c.business_id = :businessId GROUP BY c.services.id ORDER BY COUNT(c.services.readme_num) DESC")
    List<Long> mostRedeemed(@Param("businessId") Long businessId);


//(Long id, Long user_id, Long services_id, String business_id, String business_branch, String schedule_date, String schedule_time, String readme_date, String documentPath,
//    String stateName, float rate, String comment)
    @Query("SELECT new com.application.masl7tak.dto.ReadmeDTO ( " +
            "    R.id, " +
            "    R.services.id, " +
            "    R.user.id, " +
            "    R.schedule_date, " +
            "    R.schedule_time, " +
            "    R.services.products.name, " +
            "    R.services.discountValue, " +
            "    R.services.business.logo, " +
            "    R.services.business.name, " +
            "    R.business_branch, " +
            "    Br.address, " +
            "    Br.locationLink, " +
            "    Br.phone_number, " +
            "    Br.openTime, " +
            "    Br.closureTime, " +
            "    R.services.validUntil, " +
            "    R.readme_date, " +
            "    R.stateName, " +
            "    CASE WHEN R.documentPath IS NOT NULL THEN true ELSE false END, " +
            "    CASE WHEN R.comment IS NOT NULL THEN true ELSE false END," +
            "R.total_invoice," +
            "R.confirm_date, R.confirm_invoice,R.reason,R.schedule_mode,R.comment,R.promo_code_discount " +
            ") " +
            "FROM Readme R " +
            "JOIN R.user " +
            "LEFT JOIN Branches Br " +
            "WHERE Br.id = R.business_branch " +
            "GROUP BY R.id ")
    List<ReadmeDTO> findAllReadme();

    @Query("SELECT new com.application.masl7tak.dto.ReadmeDTO ( " +
            "    R.documentPath, " +
            "    R.id, " +
            "    R.services.id, " +
            "    R.user.id, " +
            "    R.schedule_date, " +
            "    R.schedule_time, " +
            "    R.services.products.name, " +
            "    R.services.discountValue, " +
            "    R.services.business.logo, " +
            "    R.services.business.name, " +
            "    R.business_branch, " +
            "    Br.address, " +
            "    Br.locationLink, " +
            "    Br.phone_number, " +
            "    Br.openTime, " +
            "    Br.closureTime, " +
            "    R.services.validUntil, " +
            "    R.readme_date, " +
            "    R.stateName, " +
            "    CASE WHEN R.documentPath IS NOT NULL THEN true ELSE false END, " +
            "    CASE WHEN R.comment IS NOT NULL THEN true ELSE false END ,R.total_invoice,R.confirm_date ,R.confirm_invoice, R.reason,R.schedule_mode ,R.comment,R.promo_code_discount " +
            ") " +
            "FROM Readme R " +
            "JOIN R.user " +
            "LEFT JOIN Branches Br " +
            "WHERE R.id = :readmeId " +
            "AND Br.id = R.business_branch " +
            "GROUP BY R.id ")
    ReadmeDTO findReadmeById(@Param("readmeId") Long readmeId);

    @Modifying
    @Query("update Readme u set u.comment = :comment ,u.rate=:rate where u.id = :readmeId")
    void update(@Param("comment") String comment,@Param("rate") Float rate,@Param("readmeId") Long readmeId);

    @Modifying
    @Query("update Readme u set u.documentPath = :path ,u.total_invoice=:total_invoice where u.id = :readmeId")
    void updateInvoicePath(@Param("path") String path,@Param("total_invoice") String total_invoice,@Param("readmeId") Long readmeId);
    @Modifying
    @Query("update Readme r set r.schedule_date = :schedule_date ,r.schedule_time=:schedule_time,r.confirm_date=:confirm_date where r.id = :readmeId")
    void coupons_date(@Param("schedule_date") String schedule_date,@Param("schedule_time") String schedule_time,
                      @Param("confirm_date") int confirm_date,@Param("readmeId") Long readmeId);
    @Modifying
    @Query("update Readme r set r.confirm_invoice = :confirmInvoice, r.reason= :reason  where r.id = :readmeId")
    void coupons_invoice(int confirmInvoice,String reason, Long readmeId);

//    (Long id, Long services_id, Long user_id, String schedule_date, String schedule_time, String service_name, Double service_discount,
//    String business_Logo, Long business_branch_ID, String business_branch_address, String locationLink, String branch_phone_number,
//    String branch_openTime, String branch_closureTime,
//    String branch_working_days, String service_expiration, String readme_date,
//    String stateName,CASE WHEN R.documentPath IS NOT NULL THEN true ELSE false END, CASE WHEN R.comment IS NOT NULL THEN true ELSE false END)
    @Query("SELECT new com.application.masl7tak.dto.ReadmeDTO ( " +
            "    R.id, " +
            "    R.services.id, " +
            "    R.user.id, " +
            "    R.schedule_date, " +
            "    R.schedule_time, " +
            "    R.services.products.name, " +
            "    R.services.discountValue, " +
            "    R.services.business.logo, " +
            "    R.services.business.name, " +
            "    R.business_branch, " +
            "    Br.address, " +
            "    Br.locationLink, " +
            "    Br.phone_number, " +
            "    Br.openTime, " +
            "    Br.closureTime, " +
            "    R.services.validUntil, " +
            "    R.readme_date, " +
            "    R.stateName, " +
            "    CASE WHEN R.documentPath IS NOT NULL THEN true ELSE false END, " +
            "    CASE WHEN R.comment IS NOT NULL THEN true ELSE false END ,R.total_invoice,R.confirm_date,R.confirm_invoice ,R.reason,R.schedule_mode ,R.comment,R.promo_code_discount " +
            ") " +
            "FROM Readme R " +
            "JOIN R.user " +
            "LEFT JOIN Branches Br " +
            "WHERE R.user.id = :userId " +
            "AND Br.id = R.business_branch " +
            "GROUP BY R.id ")
    List<ReadmeDTO> findUserCoupons(Long userId);

    @Query("SELECT new com.application.masl7tak.dto.ReadmeDTO ( " +
            "    R.id, " +
            "    R.services.id, " +
            "    R.user.id, " +
            "    R.user.name, " +
            "    R.user.contactNumber, " +
            "    R.user.image, " +
            "    R.schedule_date, " +
            "    R.schedule_time, " +
            "    R.services.products.name, " +
            "    R.services.discountValue, " +
            "    R.services.business.logo, " +
            "    R.services.business.name, " +
            "    R.business_branch, " +
            "    Br.address, " +
            "    Br.locationLink, " +
            "    Br.phone_number, " +
            "    Br.openTime, " +
            "    Br.closureTime, " +
            "    R.services.validUntil, " +
            "    R.readme_date, " +
            "    R.stateName, " +
            "    CASE WHEN R.documentPath IS NOT NULL THEN true ELSE false END, " +
            "    CASE WHEN R.comment IS NOT NULL THEN true ELSE false END ,R.total_invoice ," +
            "    R.documentPath ,R.confirm_date,R.confirm_invoice,R.reason,R.schedule_mode ,R.comment,R.promo_code_discount ) " +
            "FROM Readme R " +
            "JOIN R.user " +
            "LEFT JOIN Branches Br " +
            "WHERE R.business_id = :businessId " +
            "AND  R.schedule_date = :date " +
            "AND Br.id = R.business_branch " +
            "GROUP BY R.id ")
    List<ReadmeDTO> findBusinessCoupons(Long businessId,String date);
    @Query("SELECT new com.application.masl7tak.dto.ReadmeDTO ( " +
            "    R.id, " +
            "    R.services.id, " +
            "    R.user.id, " +
            "    R.user.name, " +
            "    R.user.contactNumber, " +
            "    R.user.image, " +
            "    R.schedule_date, " +
            "    R.schedule_time, " +
            "    R.services.products.name, " +
            "    R.services.discountValue, " +
            "    R.services.business.logo, " +
            "    R.services.business.name, " +
            "    R.business_branch, " +
            "    Br.address, " +
            "    Br.locationLink, " +
            "    Br.phone_number, " +
            "    Br.openTime, " +
            "    Br.closureTime, " +
            "    R.services.validUntil, " +
            "    R.readme_date, " +
            "    R.stateName, " +
            "    CASE WHEN R.documentPath IS NOT NULL THEN true ELSE false END, " +
            "    CASE WHEN R.comment IS NOT NULL THEN true ELSE false END ,R.total_invoice ," +
            "    R.documentPath ,R.confirm_date,R.confirm_invoice,R.reason,R.schedule_mode ,R.comment,R.promo_code_discount ) " +
            "FROM Readme R " +
            "JOIN R.user " +
            "LEFT JOIN Branches Br " +
            "WHERE R.business_id = :businessId " +
            "AND Br.id = R.business_branch " +
            "GROUP BY R.id ")
    List<ReadmeDTO> findBusinessCouponsIDs(Long businessId);
    @Query("SELECT c.services.id FROM Readme c WHERE c.business_id = :businessId GROUP BY c.services.id ORDER BY COUNT(c.services.visit_num) DESC")

    List<Long> most_visited(Long businessId);


    Long countByDate(LocalDate date);
    @Query("SELECT new com.application.masl7tak.dto.ReadmeDTO ( " +
            "    R.id, " +
            "    R.services.id, " +
            "    R.user.id, " +
            "    R.user.name, " +
            "    R.user.contactNumber, " +
            "    R.user.image, " +
            "    R.schedule_date, " +
            "    R.schedule_time, " +
            "    R.services.products.name, " +
            "    R.services.discountValue, " +
            "    R.services.business.logo, " +
            "    R.services.business.name, " +
            "    R.business_branch, " +
            "    Br.address, " +
            "    Br.locationLink, " +
            "    Br.phone_number, " +
            "    Br.openTime, " +
            "    Br.closureTime, " +
            "    R.services.validUntil, " +
            "    R.readme_date, " +
            "    R.stateName, " +
            "    CASE WHEN R.documentPath IS NOT NULL THEN true ELSE false END, " +
            "    CASE WHEN R.comment IS NOT NULL THEN true ELSE false END ,R.total_invoice ," +
            "    R.documentPath ,R.confirm_date,R.confirm_invoice,R.reason,R.schedule_mode ,R.comment,R.promo_code_discount ) " +
            "FROM Readme R " +
            "JOIN R.user " +
            "LEFT JOIN Branches Br " +
            "WHERE R.id = :readmeId " +
            "AND Br.id = R.business_branch " +
            "GROUP BY R.id ")
    ReadmeDTO findReadme(Long readmeId);
    @Modifying
    @Query("update Readme b set b.rate = ((b.rate+ :rate)/2)  where b.id = :readmeId")
    void updateRate(Float rate, Long readmeId);
}