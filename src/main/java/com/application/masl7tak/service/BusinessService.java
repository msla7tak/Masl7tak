package com.application.masl7tak.service;

import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Business;
import com.application.masl7tak.dto.BusinessDTO;
import com.application.masl7tak.model.BusinessBranch;
import com.application.masl7tak.model.filter.BusinessFilter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BusinessService {
    public ResponseEntity <List<BusinessDTO>> findAll() ;
    public ResponseEntity<BusinessDTO> findById(Long id);
    public ResponseEntity<Object> save(BusinessBranch business) ;
    public ResponseEntity<Object> update(BusinessBranch business) ;
    public void deleteById(Long id) ;

    public ResponseEntity<Object> findBusinessTermsConditions(Long id);
    public ResponseEntity<SuccessDTO> save(Business business);

    ResponseEntity<List<BusinessDTO>> findBusinessByCriteria(BusinessFilter criteria);

    ResponseEntity<AnalyticsDTO> findAnalyticsById(Long id);


    ResponseEntity<Object> create(Map<String, String> business, Long userId);

    ResponseEntity<List<BusinessDTO>> getAll();

    ResponseEntity<String> active(Long id);

    ResponseEntity<Object> findMostVisited();

    ResponseEntity<List<BusinessDTO>> findBusinessByTopRated(BusinessFilter criteria);

    ResponseEntity<String> topRate(long longId, Business business);
}
