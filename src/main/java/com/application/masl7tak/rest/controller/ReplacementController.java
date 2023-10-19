package com.application.masl7tak.rest.controller;

import com.application.masl7tak.model.Replacement;
import com.application.masl7tak.rest.api.ReplacementAPI;
import com.application.masl7tak.service.ReplacementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ReplacementController implements ReplacementAPI {
    private final ReplacementService replacementService;
    @Autowired
    public ReplacementController(ReplacementService replacementService) {
        this.replacementService = replacementService;
    }

    @Override
    public ResponseEntity<List<Replacement>> findReplacement() {

        return replacementService.findAll();
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Replacement> findById(@PathVariable Long id) {
        return replacementService.findById(id);
    }

    @Override
    public ResponseEntity<Object>  save(@RequestBody Replacement replacement) {
        log.info("test: "+replacement);
        return replacementService.save(replacement);
    }

    @Override
    public ResponseEntity<Object> update(@RequestBody Replacement replacement, @PathVariable Long id) {
        log.info("test: "+replacement);
        replacement.setId(id);
        return replacementService.save(replacement);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        replacementService.deleteById(id);
    }
}
