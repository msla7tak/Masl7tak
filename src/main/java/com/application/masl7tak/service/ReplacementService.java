package com.application.masl7tak.service;

import com.application.masl7tak.model.Replacement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReplacementService {


    public ResponseEntity <List<Replacement>>  findAll();

    public ResponseEntity<Replacement> findById(Long id);

    public ResponseEntity<Object> save(Replacement replacement);

    public void deleteById(Long id);


}
