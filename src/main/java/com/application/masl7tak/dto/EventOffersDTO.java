package com.application.masl7tak.dto;

import com.application.masl7tak.model.Services;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Transactional
@NoArgsConstructor

public class EventOffersDTO {
    private Long id;
    private String event_title;
    private String event_sub_title;
    private String image;
    private String name;
    private String logo;
    private Long business_id;

    public EventOffersDTO(Long id, String event_title, String event_sub_title, String image) {
        this.id = id;
        this.event_title = event_title;
        this.event_sub_title = event_sub_title;
        this.image = image;
    }

    public EventOffersDTO(Long id, String event_title, String event_sub_title, String image, Long business_id,
                          String name, String logo ) {
        this.id = id;
        this.event_title = event_title;
        this.event_sub_title = event_sub_title;
        this.image = image;
        this.business_id = business_id;
        this.logo = logo;
        this.name = name;
    }






    public EventOffersDTO(Long id, String event_title, String event_sub_title, String image ,Long business_id){
        this.id = id;
        this.event_title = event_title;
        this.event_sub_title = event_sub_title;
        this.image = image;
        this.business_id = business_id;


    }



//    public EventOffersDTO(Long id, String event_title, String event_sub_title, String image, List<Long> services) {
//        this.id = id;
//        this.event_title = event_title;
//        this.event_sub_title = event_sub_title;
//        this.image = image;
//
//        this.services = services;
//    }



//    public List<ServicesDTO> servicesWrapper(List<Services> services) {
//        List<ServicesDTO> servicesWrapper = new ArrayList<>();
//
//        for (Services service : services) {
//
//            servicesWrapper.add(new ServicesDTO(service.getId(), service.getDiscountValue(), service.getCreationDate(), service.getValidUntil(), service.getProducts().getId(), service.getBusiness().getId()));
//        }
//
//        return servicesWrapper;
//
//    }
}
