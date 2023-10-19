package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.ReplacementRepository;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.model.Replacement;
import com.application.masl7tak.service.ReplacementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ReplacementServiceImp implements ReplacementService {
    private final ReplacementRepository replacementRepository;
    @Autowired
    public ReplacementServiceImp(ReplacementRepository replacementRepository) {
        this.replacementRepository = replacementRepository;
    }

    @Override
    public ResponseEntity<List<Replacement>> findAll() {
        try {

            return new ResponseEntity<List<Replacement>>(replacementRepository.findAll(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>( new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Replacement> findById(Long id) {
        try {

            return new ResponseEntity<Replacement>(replacementRepository.findById(id).orElse(null), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new Replacement(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> save(Replacement replacement) {
        try {

            return new ResponseEntity<>(replacementRepository.save(replacement), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),1001), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public void deleteById(Long id) {
        replacementRepository.deleteById(id);
    }






}
