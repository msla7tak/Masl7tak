package com.application.masl7tak.Repository;

import com.application.masl7tak.model.Business;
import com.application.masl7tak.model.Replacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplacementRepository extends JpaRepository<Replacement, Long> {
    
}