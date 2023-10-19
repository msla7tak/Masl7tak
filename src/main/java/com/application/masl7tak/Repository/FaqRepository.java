package com.application.masl7tak.Repository;

import com.application.masl7tak.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
    @Query("select f from Faq f where f.answer_en=null ")

    List<Faq> findAllSupport();
    @Query("SELECT f FROM Faq f WHERE f.status = 1")

    List<Faq> findAllWithFilter();


}
