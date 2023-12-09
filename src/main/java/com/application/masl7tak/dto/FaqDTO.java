package com.application.masl7tak.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
@NoArgsConstructor
public class FaqDTO {


    private Long id;

    private  String name;
    private  String email;
    private  String question_en;
    private  String answer_en;
    private Long user_id;
    private  int status;

    public FaqDTO(Long id, String name, String email, String question_en, String answer_en, Long user_id, int status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.question_en = question_en;
        this.answer_en = answer_en;
        this.user_id = user_id;
        this.status = status;
    }
}
