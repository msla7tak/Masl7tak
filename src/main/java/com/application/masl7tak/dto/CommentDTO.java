package com.application.masl7tak.dto;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class CommentDTO {

    public Long id;

    public String name;
    public Long user_id;
    public String comment;
    public String business_name;


    public CommentDTO(Long id, String name, Long user_id, String comment, String business_name) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.comment = comment;
        this.business_name = business_name;
    }
}
