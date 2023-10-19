package com.application.masl7tak.rest.api;

import com.application.masl7tak.dto.BranchesDTO;
import com.application.masl7tak.model.Branches;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
public interface BranchesAPI {
    @GetMapping("/public/branches")
    public ResponseEntity<List<BranchesDTO>> getAllBranchess(@Param("business_id") Long business_id);

    //    @GetMapping
//    public ResponseEntity<Branches> getBranchesById(@Param("business_id") Long business_id);

    @PostMapping("/business/branches")
    public ResponseEntity<String> createBranches( @RequestBody Branches branches);


    @PutMapping("business/branches/{id}")
    public ResponseEntity<String> updateBranches(@PathVariable Long id, @Validated @RequestBody Branches updatedBranches);

    @DeleteMapping("/business/branches/{id}")
    public ResponseEntity<Void> deleteBranches(@PathVariable Long id);

}