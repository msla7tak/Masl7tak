package com.application.masl7tak.rest.controller;

import com.application.masl7tak.dto.BranchesDTO;
import com.application.masl7tak.model.Branches;
import com.application.masl7tak.rest.api.BranchesAPI;
import com.application.masl7tak.service.BranchesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BranchesController implements BranchesAPI {


    private  final BranchesService  branchesService;
    @Autowired
    public BranchesController(BranchesService branchesService) {
        this.branchesService = branchesService;
    }

    @Override

    public ResponseEntity<List<BranchesDTO>> getAllBranchess(Long business_id) {
        return branchesService.findAll( business_id);
    }


    public ResponseEntity<Branches> getBranchesById(@PathVariable Long id) {
        return branchesService.findById(id);
    }

    @Override
    public ResponseEntity<String> createBranches(@Validated @RequestBody Branches branches) {
        return branchesService.save(branches);
    }

    @Override
    public ResponseEntity<String> updateBranches(@PathVariable Long id, @Validated @RequestBody Branches updatedBranches) {
        Branches existingBranches = branchesService.findById(id).getBody();
        BeanUtils.copyProperties(updatedBranches, existingBranches, "id");
        return branchesService.save(existingBranches);
    }

    @Override
    public ResponseEntity<Void> deleteBranches(@PathVariable Long id) {
        branchesService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
