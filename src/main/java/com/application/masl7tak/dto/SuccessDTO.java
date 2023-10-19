package com.application.masl7tak.dto;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
@Data
@Transactional
public class SuccessDTO implements Serializable {
    Long id;
    String massage;

    public SuccessDTO(Long ID, String massage) {
        this.id = ID;
        this.massage = massage;
    }

    @Override
    public String toString() {
        return "\nId: "+this.id+"\n"+"Massage: "+this.massage;
    }
}
