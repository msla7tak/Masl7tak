package com.application.masl7tak.service;

import com.application.masl7tak.dto.BranchesDTO;
import com.application.masl7tak.model.Branches;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BranchesService {


        public ResponseEntity<List<BranchesDTO>> findAll(Long business_id) ;

        public ResponseEntity<Branches> findById(Long id) ;
        public  ResponseEntity<String>  save(Branches branches) ;
        public void deleteById(Long id);

}
