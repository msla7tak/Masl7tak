package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.model.CarBrand;
import com.application.masl7tak.model.CarModel;
import com.application.masl7tak.model.Category;
import com.application.masl7tak.model.Services;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

    @Query("SELECT COUNT(S.id) FROM Services S")
    int countServices();
    @Query("SELECT MAX(s.discountValue) FROM Services s")
    Long findMaxValueField();
    @Query("SELECT  new com.application.masl7tak.dto.ServicesDTO(S.visit_num,S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, C.name, S.is_available, P.id, P.name, P.description, P.price, P.image, " +
            " B.email, B.status, B.subscriptionType, B.description, B.logo, B.start_discount_val,count(R.id),S.readme_num,S.max_usage,B.working_days,S.schedule_mode" +
            ",S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
            " FROM Services S " +
            "JOIN S.products P " +
            "JOIN S.business B " +
            "LEFT JOIN S.readme R " +
            "JOIN S.category C " +
            "WHERE P.id = S.products.id AND B.id = S.business.id AND C.id = S.category.id and S.id=:id")
    ServicesDTO findBy_Id(@Param("id") Long id);
    @Query("SELECT  new com.application.masl7tak.dto.ServicesDTO(S.visit_num,S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, C.name, S.is_available, P.id, P.name, P.description, P.price, P.image, " +
            " B.email, B.status, B.subscriptionType, B.description, B.logo, B.start_discount_val,count(R.id),S.readme_num,S.max_usage,B.working_days,S.schedule_mode" +
            ",S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
            " FROM Services S " +
            "JOIN S.products P " +
            "JOIN S.business B " +
            "LEFT JOIN S.readme R " +
            "JOIN S.category C " +
            "WHERE P.id = S.products.id  " +
            "AND B.id = S.business.id AND C.id = S.category.id and S.id=:id "
          )
    Optional<ServicesDTO> findBy_Id_date(@Param("id") Long id);
    @Modifying
    @Query("update Services b set b.visit_num = (b.visit_num +1)  where b.id = :id")
    void visits_num(Long id);
    @Query("SELECT new com.application.masl7tak.dto.ServicesDTO(S.id, S.discountValue,S.images, S.creationDate, S.validUntil,S.rate, S.category.id," +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, " +
            " C.name, S.is_available, " +
            " P.id, P.name, P.description, P.price, P.image,  B.email, B.status, B.subscriptionType," +
            " B.description,B.logo , B.start_discount_val,S.comments_num,S.readme_num,S.max_usage,B.working_days,S.schedule_mode,S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios)" +
            " FROM Services S JOIN " +
            "S.products P " +
            "JOIN S.business B " +
            "JOIN S.category C " +
            "LEFT JOIN S.readme R " +
            "WHERE P.id = S.products.id AND B.id = S.business.id AND C.id = S.category.id ")
    List<ServicesDTO> getAll_Services();

    @Query("SELECT new com.application.masl7tak.dto.ServicesDTO(S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate,S.category.id," +
            " S.carModel, S.carBrand,S.business.id, B.name, S.quantity, " +
            " C.name, S.is_available, " +
            " P.id, P.name, P.description, P.price, P.image,  B.email, B.status, B.subscriptionType," +
            " B.description,  B.logo , B.start_discount_val,S.comments_num,S.readme_num,S.max_usage,B.working_days,S.schedule_mode,S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios)" +
            " FROM Services S " +
            "JOIN S.products P " +
            "JOIN S.business B " +
            "JOIN S.category C " +
            "LEFT JOIN S.readme R " +
            "WHERE P.id = S.products.id AND B.id = S.business.id AND C.id = S.category.id ")
    List<ServicesDTO> getAll_Services(PageRequest of);

    //                                                &&& filter &&&


    @Query("SELECT DISTINCT new com.application.masl7tak.dto.ServicesDTO(S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, C.name, S.is_available, P.id, P.name, P.description, P.price, P.image, " +
            " B.email, B.status, B.subscriptionType, B.description, B.logo, B.start_discount_val,S.comments_num,S.readme_num,S.max_usage ,B.working_days,S.schedule_mode" +
            ",S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
            "FROM Services S " +
            "JOIN S.products P " +
            "JOIN S.business B " +
            "JOIN S.category C " +
            "LEFT JOIN S.readme R  " +
            "JOIN S.business.branches Br " +
            "where ((:searchKey is null OR P.name LIKE CONCAT('%', :searchKey, '%')) OR (:searchKey is null OR C.name LIKE CONCAT('%', :searchKey, '%'))) " +
            "AND (:eventOfferId is null OR S.eventOffers.id = :eventOfferId) " +
            "AND (:businessId is null OR B.id = :businessId) " +
            "AND (:categoryId is null OR C.id = :categoryId) " +
            "AND (:regionId is null OR Br.region.id = :regionId)  " +
            "AND (:cityId is null OR Br.city_id = :cityId)  " +
            "AND (:rate is null OR S.rate >= :rate)  " +
            "AND S.is_available='true' " +
            "AND STR_TO_DATE(S.validUntil, '%Y-%m-%d')>= :currentDate " +
            "AND (:minDiscountValue is null OR S.discountValue >= :minDiscountValue) " +
            "AND (:maxDiscountValue is null OR S.discountValue <= :maxDiscountValue)group by S.id" +
            " ORDER BY S.discountValue desc ")
    Page<ServicesDTO> findServicesByCriteria(
                                             @Param("eventOfferId") Long eventOfferId,
                                             @Param("businessId") Long businessId,
                                             @Param("categoryId") Long categoryId,
                                             @Param("regionId") Long regionId,
                                             @Param("cityId") Long cityId,
                                             @Param("rate") Float rate,
                                             @Param("minDiscountValue") Double minDiscountValue,
                                             @Param("maxDiscountValue") Double maxDiscountValue,
                                             @Param("searchKey") String searchKey, LocalDate currentDate,
                                             PageRequest offset);
    @Query("SELECT DISTINCT new com.application.masl7tak.dto.ServicesDTO(S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, C.name, S.is_available, P.id, P.name, P.description, P.price, P.image, " +
            " B.email, B.status, B.subscriptionType, B.description, B.logo, B.start_discount_val,S.comments_num,S.readme_num,S.max_usage ,B.working_days" +
            ",S.schedule_mode,S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
            "FROM Services S " +
            "JOIN S.products P " +
            "JOIN S.business B " +
            "JOIN S.category C " +
            "LEFT JOIN S.readme R  " +
            "JOIN S.business.branches Br " +
            " JOIN CarBrandEntity eb  " +
            " JOIN CarModelEntity em  " +
            "where ((:searchKey is null OR P.name LIKE CONCAT('%', :searchKey, '%')) OR (:searchKey is null OR C.name LIKE CONCAT('%', :searchKey, '%'))) " +
            "AND (:eventOfferId is null OR S.eventOffers.id = :eventOfferId) " +
            "AND (:businessId is null OR B.id = :businessId) " +
            "AND (:categoryId is null OR C.id = :categoryId) " +
            "AND (:regionId is null OR Br.region.id = :regionId)  " +
            "AND (:cityId is null OR Br.city_id = :cityId)  " +
            "AND (:rate is null OR S.rate >= :rate)  " +
            "AND ( " +
            "    :carBrand is null OR " +
            "    (eb.services.id = S.id AND eb.brandId = :carBrand) and " +
            "    (:carModel is null OR em.services.id = S.id AND em.modelId = :carModel OR ( em.services.id = S.id AND em.modelId = 0 ))" +
            ") " +
            "AND S.is_available='true' " +
            "AND STR_TO_DATE(S.validUntil, '%Y-%m-%d')>= :currentDate " +
            "AND (:minDiscountValue is null OR S.discountValue >= :minDiscountValue) " +
            "AND (:maxDiscountValue is null OR S.discountValue <= :maxDiscountValue)group by S.id" +
            " ORDER BY S.discountValue desc ")
    Page<ServicesDTO> findServicesByCriteriaModel(
                                             @Param("eventOfferId") Long eventOfferId,
                                             @Param("businessId") Long businessId,
                                             @Param("categoryId") Long categoryId,
                                             @Param("regionId") Long regionId,
                                             @Param("cityId") Long cityId,
                                             @Param("rate") Float rate,
                                             @Param("carModel") Long carModel,
                                             @Param("carBrand") Long carBrand,
                                             @Param("minDiscountValue") Double minDiscountValue,
                                             @Param("maxDiscountValue") Double maxDiscountValue,
                                             @Param("searchKey") String searchKey, LocalDate currentDate,
                                             PageRequest offset);
    @Modifying
    @Query("update Services b set b.rate = ((b.rate+ :rate)/2)  where b.id = :readmeId")
    void updateRate(Float rate, Long readmeId);
    @Query("SELECT DISTINCT new com.application.masl7tak.dto.ServicesDTO(S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, C.name, S.is_available, P.id, P.name, P.description, P.price, P.image, " +
            " B.email, B.status, B.subscriptionType, B.description, B.logo, B.start_discount_val" +
            ",S.comments_num,S.readme_num,S.max_usage ,B.working_days,S.schedule_mode," +
            "((SELECT C.name from CarBrand C where S.carBrand= C.id )),S.eventOffers.id,S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
            "FROM Services S " +
            "JOIN S.products P " +
            "JOIN S.business B " +
            "JOIN S.category C " +
            "LEFT JOIN S.readme R  " +
            "JOIN S.business.branches Br " +
            "WHERE (:productId is null OR P.id = :productId) " +
            "AND ((:searchKey is null OR P.name LIKE CONCAT('%', :searchKey, '%')) OR (:searchKey is null OR C.name LIKE CONCAT('%', :searchKey, '%'))) " +
            "AND (:eventOfferId is null OR S.eventOffers.id = :eventOfferId) " +
            "AND (:businessId is null OR B.id = :businessId) " +
            "AND (:categoryId is null OR C.id = :categoryId) " +
            "AND (:regionId is null OR Br.region.id = :regionId)  " +
            "AND (:rate is null OR S.rate >= :rate)  " +
            "AND (:carModel is null OR S.carModel = :carModel) " +
            "AND (:carBrand is null OR S.carBrand = :carBrand) " +
            "AND (:minDiscountValue is null OR S.discountValue >= :minDiscountValue) " +
            "AND (:maxDiscountValue is null OR S.discountValue <= :maxDiscountValue) group by S.id")
    List<ServicesDTO> findAllAdmin(@Param("productId") Long productId,
                                             @Param("eventOfferId") Long eventOfferId,
                                             @Param("businessId") Long businessId,
                                             @Param("categoryId") Long categoryId,
                                             @Param("regionId") Long regionId,
                                             @Param("rate") Float rate,
                                             @Param("carModel") Long carModel,
                                             @Param("carBrand") Long carBrand,
                                             @Param("minDiscountValue") Double minDiscountValue,
                                             @Param("maxDiscountValue") Double maxDiscountValue,
                                             @Param("searchKey") String searchKey,
                                             PageRequest offset);

    @Modifying
    @Query("update Services b set b.comments_num = (b.comments_num +1)  where b.id = :id")
    void comments_numCount(Long id);


    @Modifying
    @Query(value = "UPDATE services s SET " +
            "s.images = COALESCE(:images, s.images), " +
            "s.discount_value = COALESCE(:discountValue, s.discount_value), " +
            "s.car_brand = COALESCE(:carBrand, s.car_brand), " +
            "s.car_model = COALESCE(:carModel, s.car_model), " +
            "s.schedule_mode = COALESCE(:schedule_mode, s.schedule_mode), " +
            "s.category_id = COALESCE(:category, s.category_id), " +
            "s.max_usage = COALESCE(:maxUsage, s.max_usage), " +
            "s.valid_until = COALESCE(:validUntil, s.valid_until), " +
            "s.is_available = COALESCE(:is_available, s.is_available) " +
            "WHERE s.id = :id", nativeQuery = true)
    void update(Long id, String images, double discountValue, Long carBrand, Long carModel,
                int maxUsage, String validUntil, String is_available, int schedule_mode, Long category);

    @Modifying
    @Query("update Services s set s.readme_num = (s.readme_num +1)  where s.id = :id")
    void readme_num(Long id);


    @Modifying
    @Query("update Services s set s.is_available = 'false'  where s.id = :id and s.readme_num=s.max_usage")
    void isAvailable(Long id);
    @Modifying
    @Query("UPDATE Business b SET b.service_count = (b.service_count - 1) WHERE b.id = :id AND :service_id IN (SELECT s.id FROM Services s WHERE s.business.id = b.id AND s.is_available = 'false')")
    void reCountService(Long id,Long service_id);
    @Modifying
    @Query("update Services b set b.is_available = :active  where b.id = :longId")
    void active(long longId, String active);
    @Query("SELECT NEW com.application.masl7tak.dto.ServicesDTO(S.visit_num,S.id, S.discountValue, S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, " +
            "C.name, S.is_available, " +
            "P.id, P.name, P.description, P.price, P.image, B.email, B.status, B.subscriptionType, " +
            "B.description, B.logo, B.start_discount_val, S.comments_num, S.readme_num, S.max_usage, B.working_days," +
            " S.schedule_mode,S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
            "FROM Services S " +
            "JOIN S.products P " +
            "JOIN S.business B " +
            "JOIN S.category C " +
            "LEFT JOIN S.readme R " +
            "WHERE P.id = S.products.id AND B.id = S.business.id AND C.id = S.category.id " +
            "GROUP BY S.id ORDER BY S.visit_num DESC")

    List<ServicesDTO> findMostVisited();

    @Query("SELECT B.brandId  " +
            "FROM  CarBrandEntity B " +
            "WHERE B.services.id = :id")

    List<String> findBrand(Long id);
    @Query("SELECT M.modelId  " +
            "FROM  CarModelEntity M " +
            "WHERE M.services.id = :id")

    List<String> findModel(Long id);
    @Modifying
    @Query("DELETE FROM CarBrandEntity c WHERE c.services.id = :id")
    void clearCarBrandEntities(Long id);
    @Modifying
    @Query("DELETE FROM CarModelEntity c WHERE c.services.id = :id")
    void clearCarModelEntities(Long id);
@Query("SELECT DISTINCT new com.application.masl7tak.dto.ServicesDTO(S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
                "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, S.category.name, S.is_available, S.products.id," +
        " S.products.name, S.products.description,S.products.price, S.products.image, " +
                " B.email, B.status, B.subscriptionType, B.description, B.logo, " +
        "B.start_discount_val,S.comments_num,S.readme_num,S.max_usage " +
        ",B.working_days,S.schedule_mode," +
        "((SELECT C.name from CarBrand C where S.carBrand= C.id )),S.eventOffers.id,S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
                "FROM Services S  " +
                "JOIN S.business B on B.id = :id " +
                "JOIN S.business.branches Br " +
                "where  B.id = :id " +
                " group by S.id")
    List<ServicesDTO> findAllBusinessServices(Long id);

    @Query("SELECT DISTINCT new com.application.masl7tak.dto.ServicesDTO(S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, S.category.name, S.is_available, S.products.id, S.products.name, S.products.description,S.products.price, S.products.image, " +
            " B.email, B.status, B.subscriptionType, B.description, B.logo, " +
            "B.start_discount_val,S.comments_num,S.readme_num,S.max_usage " +
            ",B.working_days,S.schedule_mode," +
            "((SELECT C.name from CarBrand C where S.carBrand= C.id )),S.eventOffers.id,S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
            "FROM Services S  " +
            "JOIN S.business B " +
            "JOIN S.business.branches Br " +
            "where S.eventOffers.id = :id " +
            " group by S.id")
    List<ServicesDTO> findAllEventServices(Long id);
    @Query("SELECT  new com.application.masl7tak.dto.ServicesDTO(S.visit_num,S.id, S.discountValue,S.images, S.creationDate, S.validUntil, S.rate, S.category.id, " +
            "S.carModel, S.carBrand, S.business.id, B.name, S.quantity, C.name, S.is_available, P.id, P.name, P.description, P.price, P.image, " +
            " B.email, B.status, B.subscriptionType, B.description, B.logo, B.start_discount_val,count(R.id),S.readme_num,S.max_usage,B.working_days,S.schedule_mode," +
            "S.business_app_url_android,S.business_app_promo_code,S.business_app_url_ios) " +
            " FROM Services S " +
            "JOIN S.products P " +
            "JOIN S.business B " +
            "LEFT JOIN S.readme R " +
            "JOIN S.category C " +
            "WHERE P.id = S.products.id AND B.id = S.business.id AND C.id = S.category.id and S.id=:id")
    ServicesDTO findByIdAdmin(Long id);


//            "JOIN Branches Br " +
//@Param("regionId") Long regionId,

//   "AND (:regionId is null OR Br.business.id =  S.business.id and Br.region.id=: regionId ) " +


}

