package com.application.masl7tak.Repository;

import com.application.masl7tak.model.BusinessAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessAccountRepository extends JpaRepository<BusinessAccount, Long> {
}