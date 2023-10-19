package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.Repository.BranchesRepository;
import com.application.masl7tak.dto.BranchesDTO;
import com.application.masl7tak.model.Branches;
import com.application.masl7tak.service.BranchesService;
import com.application.masl7tak.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class BranchesServiceImp implements BranchesService {
    private final BranchesRepository branchesRepository;
    @Autowired
    public BranchesServiceImp(BranchesRepository branchesRepository) {
        this.branchesRepository = branchesRepository;
    }

    @Override
    public ResponseEntity<List<BranchesDTO>> findAll(Long business_id) {

        try {
            return new ResponseEntity<> (branchesRepository.findBranchesByBusinessId(business_id), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Branches> findById(Long id) {


        try {
            return new ResponseEntity<>(branchesRepository.findById(id).orElse(null), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Branches(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> save(Branches branches) {

        try {
            branchesRepository.save(branches);
            return Utils.getResponseEntity(Constants.DATA_Inserted, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        branchesRepository.deleteById(id);

    }

}
