package com.application.masl7tak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Faq")
public class Faq {
    private static final long serialVersionUID = 1L;
    private static final int STATUS_PRIVATE = 0;
    private static final int STATUS_PUBLIC = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private  String name;
    @Column(name = "email")
    private  String email;
    @Column(name = "question_en")
    private  String question_en;
    @Column(name = "question_ar")
    private  String question_ar;
    @Column(name = "answer_en")
    private  String answer_en;
    @Column(name = "answer_ar")
    private  String answer_ar;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "status")
    private  int status;

    public Faq(String name, String email, String questionEn, int status,Long user_id) {
        this.name = name;
        this.email = email;
        this.question_en = questionEn;
        this.status = status;
        this.user_id = user_id;

    }


}
