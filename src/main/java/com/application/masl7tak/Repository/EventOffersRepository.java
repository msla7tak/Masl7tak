package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.EventOffersDTO;
import com.application.masl7tak.model.EventOffers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventOffersRepository extends JpaRepository<EventOffers, Long> {

//    (Long id, String event_title, String event_sub_title, String description, java.sql.Date creation_date,
//    Date valid_until, List<Services> servicesWrapper) {

    @Query(value = "SELECT new com.application.masl7tak.dto.EventOffersDTO" +
            "(E.id, E.event_title, E.event_sub_title, E.image, B.id, B.name, B.logo) " +
            "FROM EventOffers E JOIN E.business B WHERE B.id = E.business.id")
    List<EventOffersDTO> get_business_events();


    @Query(value = "SELECT new com.application.masl7tak.dto.EventOffersDTO(E.id, E.event_title, E.event_sub_title, E.image,E.business.id) FROM EventOffers E where E.business.id= null ")
    List<EventOffersDTO> getAll_EventOffers();

    @Query("SELECT s.id FROM EventOffers e JOIN e.services s WHERE e.id = :id")
    List<Long> findAllServiceIdsByEventOfferId(@Param("id") Long id);



@Query(value = "SELECT new com.application.masl7tak.dto.EventOffersDTO(E.id, E.event_title, E.event_sub_title, E.image, " +
        " (SELECT s.id FROM E.services s WHERE E.id = :id)) FROM EventOffers E")

List<EventOffersDTO> findAllServicesByEventOfferId();

    @Query(value = "SELECT new com.application.masl7tak.dto.EventOffersDTO(E.id, E.event_title, E.event_sub_title, E.image) FROM EventOffers E where E.id=:id")
        EventOffersDTO findBy_Id(Long id);
}
