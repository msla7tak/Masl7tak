package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.BranchesDTO;
import com.application.masl7tak.model.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchesRepository extends JpaRepository<Branches, Long> {

    @Query("SELECT new com.application.masl7tak.dto.BranchesDTO(Br.id, Br.address, Br.locationLink, " +
            "Br.phone_number, " +
            "Br.openTime, Br.closureTime, B.id, R.id, R.name_ar, R.name_en) " +
            "FROM Branches Br " +
            "JOIN Br.business B " +
            "JOIN Br.region R " +
            "WHERE B.id = :businessId")
    List<BranchesDTO> findBranchesByBusinessId(@Param("businessId") Long businessId);

}