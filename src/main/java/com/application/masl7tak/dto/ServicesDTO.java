package com.application.masl7tak.dto;

import com.application.masl7tak.Repository.ServicesRepository;
import com.application.masl7tak.model.CarBrandEntity;
import com.application.masl7tak.model.CarModelEntity;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Transactional
public class ServicesDTO {

    private  double visit_num;
    private Long id;
    private double discount_value;
    private String service_images;
    private String numberOfComments;
    private Long brand_id;
    private Long model_id;
    private Long event_id;
    private int quantity;
    private String creationDate;
    private String validUntil;
    private String is_available;
    private float rate;
    private ProductDTO products = null;
    private Long category_id;
    private String category_name;
    private String brand_name;
    private BusinessDTO business;
    private double readme_num;
    private int max_usage;
    private int schedule_mode;
    private List<String> carBrandEntities;


    private List<String> carModelEntities;

    public ServicesDTO(Long id, double discount_value,String service_images, String creationDate, String validUntil,float rate, Long category_id,Long model_id, Long brand_id,
                       Long business_id, String business_name, int quantity, String category_name,
                       String is_available, Long id_p, String name, String description, double price, String image,
                      String email, String status, String subscriptionType,
                       String businessDescription, String logo
                       ,double start_discount_val,Long numberOfComments,double readme_num,int max_usage,String working_days, int schedule_mode) {
        this.id = id;
        this.discount_value = discount_value;
        this.creationDate = creationDate;
        this.model_id = model_id;
        this.brand_id = brand_id;
        this.validUntil = validUntil;
        this.category_id = category_id;
        this.rate = rate;
        this.service_images = service_images;
        this.category_name = category_name;
        this.is_available = is_available;
        this.quantity = quantity;
        this.numberOfComments = numberOfComments.toString();
        this.readme_num = readme_num;
        this.max_usage = max_usage;
        this.schedule_mode = schedule_mode;
        this.business= new BusinessDTO( business_id,  business_name,    email,  status,  subscriptionType,
                businessDescription,  logo, start_discount_val, working_days);
        this.products = new ProductDTO( id_p,  name,  description,  price,  image);
    }
 public ServicesDTO(double visit_num,Long id, double discount_value,String service_images, String creationDate, String validUntil,float rate, Long category_id,Long model_id, Long brand_id,
                       Long business_id, String business_name, int quantity, String category_name,
                       String is_available, Long id_p, String name, String description, double price, String image,
                      String email, String status, String subscriptionType,
                       String businessDescription, String logo
                       ,double start_discount_val,Long numberOfComments,double readme_num,int max_usage,String working_days, int schedule_mode) {
        this.id = id;
        this.discount_value = discount_value;
        this.creationDate = creationDate;
        this.model_id = model_id;
        this.brand_id = brand_id;
        this.validUntil = validUntil;
        this.category_id = category_id;
        this.rate = rate;
        this.service_images = service_images;
        this.category_name = category_name;
        this.is_available = is_available;
        this.quantity = quantity;
        this.numberOfComments = numberOfComments.toString();
        this.readme_num = readme_num;
        this.max_usage = max_usage;
        this.schedule_mode = schedule_mode;
        this.visit_num = visit_num;

        this.business= new BusinessDTO( business_id,  business_name,    email,  status,  subscriptionType,
                businessDescription,  logo, start_discount_val, working_days);
        this.products = new ProductDTO( id_p,  name,  description,  price,  image);
    }

//


    public ServicesDTO(Long id, double discount_value,String service_images, Long brand_id, Long model_id, int quantity, String creationDate,
                       String validUntil, String is_available, float rate, Long category_id,Long numberOfComments,double readme_num,int max_usage, int schedule_mode) {
        this.id = id;
        this.discount_value = discount_value;
        this.brand_id = brand_id;
        this.model_id = model_id;
        this.quantity = quantity;
        this.service_images = service_images;
        this.creationDate = creationDate;
        this.validUntil = validUntil;
        this.is_available = is_available;
        this.rate = rate;
        this.numberOfComments = numberOfComments.toString();

        this.category_id = category_id;
        this.readme_num = readme_num;
        this.max_usage = max_usage;
        this.schedule_mode = schedule_mode;
    }
    public ServicesDTO(Long id, double discount_value,String service_images, String creationDate, String validUntil,float rate, Long category_id,Long model_id, Long brand_id,
                       Long business_id, String business_name, int quantity, String category_name,
                       String is_available, Long id_p, String name, String description, double price, String image,
                       String email, String status, String subscriptionType,
                       String businessDescription, String logo
            ,double start_discount_val,Long numberOfComments,double readme_num,int max_usage,String working_days, int schedule_mode,String brand_name,Long event_id) {
        this.id = id;
        this.discount_value = discount_value;
        this.creationDate = creationDate;
        this.model_id = model_id;
        this.brand_id = brand_id;
        this.validUntil = validUntil;
        this.category_id = category_id;
        this.rate = rate;
        this.service_images = service_images;
        this.category_name = category_name;
        this.is_available = is_available;
        this.quantity = quantity;
        this.numberOfComments = numberOfComments.toString();
        this.readme_num = readme_num;
        this.max_usage = max_usage;
        this.schedule_mode = schedule_mode;
        this.brand_name = brand_name;
        this.event_id = event_id;

        this.business= new BusinessDTO( business_id,  business_name,    email,  status,  subscriptionType,
                businessDescription,  logo, start_discount_val, working_days);
        this.products = new ProductDTO( id_p,  name,  description,  price,  image);
    }

    public ServicesDTO() {

    }
}
