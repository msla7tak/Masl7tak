package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.FaqDTO;
import com.application.masl7tak.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
    @Query("select f from Faq f where f.answer_ar=null ")

    List<Faq> findAllSupport();
//    (Long id, String name, String email, String question_en, String answer_en, Long user_id, int status)
@Query(value = "SELECT new com.application.masl7tak.dto.FaqDTO(f.id, f.name, f.email, f.question_en, f.answer_en, f.user_id, f.status ) " +
        " FROM  Faq f where f.status= 1")

    List<FaqDTO> findAllWithFilter();@Query(value = "SELECT new com.application.masl7tak.dto.FaqDTO(f.id, f.name, f.email, f.answer_ar, f.answer_ar, f.user_id, f.status ) " +
        " FROM  Faq f where f.status= 1")

    List<FaqDTO> findAllWithFilter_en();
    @Query("SELECT f FROM Faq f WHERE f.user_id = :user_id")

    List<Faq> findFaqByUser_idIs(Long user_id);


}
