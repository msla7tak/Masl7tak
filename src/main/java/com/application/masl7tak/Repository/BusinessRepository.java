package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.model.Business;
import com.application.masl7tak.dto.BusinessDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    @Query("SELECT COUNT(B.id) FROM Business B")
    int countBusinesses();

    @Query("SELECT DISTINCT new com.application.masl7tak.dto.BusinessDTO(B.id, B.name,  B.email, B.status, B.subscriptionType," +
            "B.description, B.logo,B.service_count,B.start_discount_val,B.rate," +
            "(SELECT C.id from Category C where B.category.id= C.id ), " +
            " MIN(Br.id) ,B.visits_num ,B.working_days)" +
            "FROM Business B " +
            "JOIN Branches Br ON B.id = Br.business.id " +
            "LEFT JOIN B.services S " +
            "WHERE (:searchKey is null OR B.name  LIKE %:searchKey%) " +
            "AND (:categoryId is null OR B.category.id = :categoryId) " +
            "AND (:regionId is null OR Br.region.id = :regionId)  " +
            "AND (:cityId is null OR Br.city_id = :cityId)  " +
            "AND B.status=1 " +
            "AND (:rate is null OR B.rate >= :rate)  " +
            "GROUP BY B.id")
    List<BusinessDTO> findBusinessByCriteria(   @Param("categoryId") Long categoryId,
                                                @Param("regionId") Long regionId,
                                                @Param("cityId") Long cityId,
                                                @Param("rate") Float rate,
                                                @Param("searchKey")  String searchKey,
                                             PageRequest of);
    @Query("SELECT DISTINCT new com.application.masl7tak.dto.BusinessDTO(B.id, B.name,  B.email, B.status, B.subscriptionType," +
            "B.description, B.logo,B.service_count,B.start_discount_val,B.rate," +
            "(SELECT C.id from Category C where B.category.id= C.id ), " +
            " MIN(Br.id) ,B.visits_num ,B.working_days)" +
            "FROM Business B " +
            "JOIN Branches Br ON B.id = Br.business.id " +
            "LEFT JOIN B.services S " +
            "WHERE (:searchKey is null OR B.name  LIKE %:searchKey%) " +
            "AND (:categoryId is null OR B.category.id = :categoryId) " +
            "AND (:regionId is null OR Br.region.id = :regionId)  " +
            "AND (:cityId is null OR Br.city_id = :cityId)  " +
            "AND (:rate is null OR B.rate >= :rate)  " +
            "GROUP BY B.id")
    List<BusinessDTO> findBusinessByCriteriaAdmin(   @Param("categoryId") Long categoryId,
                                                @Param("regionId") Long regionId,
                                                @Param("cityId") Long cityId,
                                                @Param("rate") Float rate,
                                                @Param("searchKey")  String searchKey,
                                             PageRequest of);


    @Query("SELECT new com.application.masl7tak.dto.BusinessDTO(B.id, B.name,B.email, B.status, B.subscriptionType," +
            " B.description, B.logo, B.service_count,(SELECT C.id from Category C where B.category.id= C.id ),B.visits_num,B.working_days)" +
            " FROM  Business B LEFT JOIN B.services S GROUP BY B.id")
    List<BusinessDTO> getAllBusiness();
    Business findByEmail(@Param("email") String email);

    @Query("SELECT new com.application.masl7tak.dto.BusinessDTO(B.id, B.name,  B.email, B.status, B.subscriptionType, " +
            "B.description, B.logo, B.service_count, B.start_discount_val, B.rate, B.category.id, " +
            "MIN(Br.id), B.visits_num ,B.working_days,B.termsConditions) " +
            "FROM Business B " +
            "JOIN Branches Br ON B.id = Br.business.id " +
            "LEFT JOIN B.services S ON  B.id = S.business.id and S.is_available ='true' " + // Use LEFT JOIN here
            "WHERE B.id = :business_id  ")
    BusinessDTO getBusinessDTOByBusinessId(Long business_id);



    @Query("select DISTINCT B from Business B LEFT JOIN FETCH B.branches")
    List<Business> getAll(PageRequest of);
    @Query("select  B.termsConditions from Business B where B.id=:id")
    String findBusinessTermsConditions(Long id);

    @Modifying
    @Query("update Business b set b.visits_num = (b.visits_num +1)  where b.id = :id")
    void visits_num(Long id);
    @Modifying
    @Query("update Business b set b.status = :active  where b.id = :id")
    void active(Long id,int active);
    @Modifying
    @Query("update Business b set b.start_discount_val = :value  where b.id = :id")
    void startDiscountVal(Long id,Double value);

//    @Query("select new com.application.masl7tak.dto.AnalyticsDTO( COUNT(R.id),COUNT( DISTINCT R.user.id),B.visits_num,COUNT(S.business.id) )" +
//            "from Business B " +
//            "left JOIN B.services S ON  B.id = S.business.id " +
//            "left JOIN Readme R ON B.id = R.services.business.id " +
//            "where B.id=:id")
//    AnalyticsDTO findAnalyticsById(Long id);

    @Query("SELECT new com.application.masl7tak.dto.AnalyticsDTO(" +
            " COUNT(DISTINCT R.id) , " +
            " COUNT(DISTINCT R.user.id) , " +
            " COUNT(DISTINCT S.id), " +
            "B.visits_num) " +
            "FROM Business B " +
            "LEFT JOIN B.services S ON  S.business.id =:id " +
            "LEFT JOIN Readme R ON  R.business_id =:id " +
            "WHERE B.id = :id")
    AnalyticsDTO findAnalyticsById(Long id);

    @Query("SELECT  new com.application.masl7tak.dto.BusinessDTO(B.id, B.name) FROM Business B")
    List<BusinessDTO> getAll();
    @Query("SELECT DISTINCT new com.application.masl7tak.dto.BusinessDTO(B.id, B.name,  B.email, B.status, B.subscriptionType, " +
            "B.description, B.logo, B.service_count, B.start_discount_val, B.rate, B.category.id, " +
            "MIN(Br.id), B.visits_num ,B.working_days) " +
            "FROM Business B " +
            "JOIN Branches Br ON B.id = Br.business.id " +
            "LEFT JOIN B.services S ON  B.id = S.business.id and S.is_available ='true' " + // Use LEFT JOIN here
            "GROUP BY B.id ORDER BY B.visits_num DESC ")
    List<BusinessDTO> findMostVisited();
    @Modifying
    @Query("update Business b set b.rate = ((b.rate+ :rate)/2)  where b.id = :businessId")
    void updateRate(Float rate, Long businessId);
    @Modifying
    @Query("update Business b set b.service_count = (b.service_count +1)  where b.id = :id")
    void serviceCount(Long id);
    @Modifying
    @Query("update Business b set b.service_count = (b.service_count -1)  where b.id = :id")
    void serviceDeCount(Long id);
    @Query("SELECT DISTINCT new com.application.masl7tak.dto.BusinessDTO(B.id, B.name,  B.email, B.status, B.subscriptionType," +
            "B.description, B.logo,B.service_count,B.start_discount_val,B.rate," +
            "(SELECT C.id from Category C where B.category.id= C.id ), " +
            " MIN(Br.id) ,B.visits_num ,B.working_days)" +
            "FROM Business B " +
            "JOIN Branches Br ON B.id = Br.business.id " +
            "LEFT JOIN B.services S " +
            "WHERE (:searchKey is null OR B.name  LIKE %:searchKey%) " +
            "AND  B.top_rate=1 " +
            "AND (:categoryId is null OR B.category.id = :categoryId) " +
            "AND (:regionId is null OR Br.region.id = :regionId)  " +
            "AND (:rate is null OR B.rate >= :rate)  " +
            "GROUP BY B.id")
    List<BusinessDTO> findBusinessTopRated(   @Param("categoryId") Long categoryId,
                                                @Param("regionId") Long regionId,
                                                @Param("rate") Float rate,
                                                @Param("searchKey")  String searchKey,
                                                PageRequest of);
    @Modifying
    @Query("update Business b set b.top_rate = :topRate  where b.id = :longId")
    void topRate(long longId, int topRate);


//    @Modifying
//    @Query("UPDATE Business b SET b.start_discount_val =: discount_val WHERE b.id = :id AND b.start_discount_val < :discount_val")
//    void updateStartDiscountVal(@Param("id") Long id, @Param("discount_val") double discountVal);


}