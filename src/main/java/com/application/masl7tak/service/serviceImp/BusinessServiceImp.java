package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.*;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.AnalyticsDTO;
import com.application.masl7tak.dto.BusinessDTO;
import com.application.masl7tak.dto.ServicesDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.*;
import com.application.masl7tak.model.filter.BusinessFilter;
import com.application.masl7tak.service.BusinessService;
import com.application.masl7tak.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
@Transactional
public class BusinessServiceImp implements BusinessService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ReadmeRepository readmeRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private BranchesRepository branchesRepository;
    @Autowired
    private JwtAuthFilter JwtAuthFilter;


    @Override
    public ResponseEntity<List<BusinessDTO>> findAll() {
        try {
            return new ResponseEntity<>(businessRepository.getAllBusiness(), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<BusinessDTO> findById(Long id) {
        try {

            businessRepository.visits_num(id);
            return new ResponseEntity<>(businessRepository.getBusinessDTOByBusinessId(id), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return new ResponseEntity<>(new BusinessDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<Object> save(BusinessBranch businessBranch) {
        try {
            log.error(businessBranch + "");
            Business business = businessRepository.findByEmail(businessBranch.getEmail());

            if (Objects.isNull(business)) {
                business = new Business();
                business.setName(businessBranch.getName());
                business.setEmail(businessBranch.getEmail());
                business.setLogo(businessBranch.getLogo());
                business.setRate(5);
                business.setTermsConditions(businessBranch.getTermsConditions());
                business.setDescription(businessBranch.getDescription());
                business.setSubscriptionType(businessBranch.getSubscriptionType());
                business.setStatus(businessBranch.getStatus());
                business.setStart_discount_val(businessBranch.getStart_discount_val());
                business.setCategory(businessBranch.getCategory());
                business.setWorking_days(businessBranch.getWorking_days());

                business.setStatus("active");
                business.setSubscriptionType("junior");

                business = businessRepository.save(business);
                if (businessBranch.getBranches() != null)
                    for (Branches branch : businessBranch.getBranches()) {
                        branch.setBusiness(business);
                        branchesRepository.save(branch);
                    }

                userRepository.updateRoleByEmail(JwtAuthFilter.getCurrentUser(), business.getId());


                return new ResponseEntity<>(new SuccessDTO(business.getId(), Constants.DATA_Inserted), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new SuccessDTO(0L, "Email  already exits"), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 105), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<Object> update(BusinessBranch businessBranch) {
        try {
            User user = userRepository.findUserByEmail(JwtAuthFilter.getCurrentUser()).orElseThrow();


          ;
            // Fetch the existing Business entity from the repository
            Business business = businessRepository.findById(  user.getBusiness_id())
                    .orElseThrow(() -> new EntityNotFoundException("Business not found"));
            businessBranch.setId( user.getBusiness_id());
            log.error(businessBranch + "");
            // Update the Business entity with values from businessBranch
            business.setName(businessBranch.getName());
            business.setEmail(businessBranch.getEmail());
            business.setLogo(businessBranch.getLogo());
            business.setTermsConditions(businessBranch.getTermsConditions());
            business.setDescription(businessBranch.getDescription());
            business.setSubscriptionType(businessBranch.getSubscriptionType());
            business.setStatus(businessBranch.getStatus());
            business.setStart_discount_val(businessBranch.getStart_discount_val());
            business.setCategory(businessBranch.getCategory());
            business.setWorking_days(businessBranch.getWorking_days());
            // Update the associated branches
            List<Branches> existingBranches = business.getBranches();

            // Loop through the new branches and update or add them
            for (Branches newBranch : businessBranch.getBranches()) {
                if (newBranch.getId() != null) {
                    // Check if the new branch exists in the existing branches
                    for (Branches existingBranch : existingBranches) {
                        if (existingBranch.getId().equals(newBranch.getId())) {

                            existingBranch.setAddress(newBranch.getAddress());
                            existingBranch.setLocationLink(newBranch.getLocationLink());
                            existingBranch.setPhone_number(newBranch.getPhone_number());
                            existingBranch.setOpenTime(newBranch.getOpenTime());
                            existingBranch.setClosureTime(newBranch.getClosureTime());
                            existingBranch.setCity_id(newBranch.getCity_id());
                            existingBranch.setRegion(newBranch.getRegion());
                        }
                    }
                } else {
                    newBranch.setBusiness(business);
                    existingBranches.add(newBranch);
                }
            }

            business.setBranches(existingBranches);
            business = businessRepository.save(business);

// Save the individual existing branches
            for (Branches existingBranch : existingBranches) {
                branchesRepository.save(existingBranch);
            }

            return new ResponseEntity<>(new SuccessDTO(business.getId(), Constants.DATA_Inserted), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 105), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public void addBranchToBusiness(Long businessId, Branches newBranch) {
        Business business = businessRepository.findById(businessId).orElse(null);

        if (business != null) {
            // Set the relationship
            newBranch.setBusiness(business);
            business.getBranches().add(newBranch);

            // Save changes
            businessRepository.save(business);
        }
    }


    public ResponseEntity<SuccessDTO> save(Business business) {
        try {
            Business businessRepositoryByEmail = businessRepository.findByEmail(business.getEmail());
            business.setRate(5);
            if (Objects.isNull(businessRepositoryByEmail)) {
                businessRepositoryByEmail = businessRepository.save(business);
                userRepository.updateRoleByEmail(JwtAuthFilter.getCurrentUser(), businessRepositoryByEmail.getId());
                return new ResponseEntity<>(new SuccessDTO(businessRepositoryByEmail.getId(), Constants.DATA_Inserted), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new SuccessDTO(0L, "Email  already exits"), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new SuccessDTO(0L, "Some Thing Want Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public Business createBusinessFromMap(Map<String, String> businessMap) {
        Business business = new Business();
        business.setStatus("active");
        business.setSubscriptionType("junior");
        // Check if the map contains the required keys before setting the fields
        if (businessMap.containsKey("name")) {
            business.setName(businessMap.get("name"));
        }
        if (businessMap.containsKey("logo")) {
            business.setLogo(businessMap.get("logo"));
        }
        if (businessMap.containsKey("email")) {
            business.setEmail(businessMap.get("email"));
        }
        if (businessMap.containsKey("working_days")) {
            business.setWorking_days(businessMap.get("working_days"));
        }
        if (businessMap.containsKey("description")) {
            business.setDescription(businessMap.get("description"));
        }
        if (businessMap.containsKey("terms_conditions")) {
            business.setTermsConditions(businessMap.get("terms_conditions"));
        }
        if (businessMap.containsKey("category_id")) {
            // Assuming category_id is of type Long
            Long categoryId = Long.parseLong(businessMap.get("category_id"));
            Category category = new Category();
            category.setId(categoryId);
            business.setCategory(category);
        }

        return business;
    }

    public void createBranchesFromMap(Map<String, String> businessMap, Business business) {
        Branches branch = new Branches();

        if (businessMap.containsKey("address")) {
            branch.setAddress(businessMap.get("address"));
        }
        if (businessMap.containsKey("locationLink")) {
            branch.setLocationLink(businessMap.get("locationLink"));
        }
        if (businessMap.containsKey("phone_number")) {
            branch.setPhone_number(businessMap.get("phone_number"));
        }
        if (businessMap.containsKey("openTime")) {
            branch.setOpenTime(businessMap.get("openTime"));
        }
        if (businessMap.containsKey("closureTime")) {
            branch.setClosureTime(businessMap.get("closureTime"));
        }

        if (businessMap.containsKey("region_id")) {
            // Assuming region_id is of type Long
            Long regionId = Long.parseLong(businessMap.get("region_id"));
            Region region = new Region();
            region.setId(regionId);
            branch.setRegion(region);
        }
        if (businessMap.containsKey("city_id")) {
            // Assuming city_id is of type Long
            Long cityId = Long.parseLong(businessMap.get("city_id"));
            branch.setCity_id(cityId);
        }
        branch.setBusiness(business);

        branchesRepository.save(branch);

    }

    @Override
    public ResponseEntity<Object> create(Map<String, String> business_map, Long userId) {
        try {
            Business business = createBusinessFromMap(business_map);
            business.setRate(5);
            Business businessRepositoryByEmail = businessRepository.findByEmail(business.getEmail());

            if (Objects.isNull(businessRepositoryByEmail)) {
                businessRepositoryByEmail = businessRepository.save(business);
                createBranchesFromMap(business_map, businessRepositoryByEmail);
                userRepository.updateRoleByEmail(userRepository.findById(userId).get().getEmail(), businessRepositoryByEmail.getId());
                return new ResponseEntity<>(new SuccessDTO(businessRepositoryByEmail.getId(), Constants.DATA_Inserted), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Constants.responseMessage("Email  already exits", 108), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 108), HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public ResponseEntity<List<BusinessDTO>> getAll() {
        try {


            return new ResponseEntity<List<BusinessDTO>>(businessRepository.getAll(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> active(Long id) {
        businessRepository.active(id, "active");
        return new ResponseEntity<>("done", HttpStatus.OK);
    }


    @Override
    public void deleteById(Long id) {
        businessRepository.deleteById(id);

    }

    @Override
    public ResponseEntity<String> findBusinessTermsConditions(Long id) {
        return new ResponseEntity<>(businessRepository.findBusinessTermsConditions(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BusinessDTO>> findBusinessByCriteria(BusinessFilter criteria) {
        try {

            Long categoryId = criteria.getCategoryId();
            Long regionId = criteria.getRegionId();
            Float rate = criteria.getRate();
            String searchKey = criteria.getSearchKey();

            int offset = criteria.getOffset();

            return new ResponseEntity<>(businessRepository.findBusinessByCriteria(categoryId, regionId, rate
                    , searchKey, PageRequest.of(offset, 100)), HttpStatus.OK);
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Map<LocalDate, Long> countReadmesForLastSixDays() {
        Map<LocalDate, Long> result = new HashMap<>();
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6);

        while (!startDate.isAfter(endDate)) {
            Long count = readmeRepository.countByDate(startDate);
            result.put(startDate, count);
            startDate = startDate.plusDays(1);
        }

        return result;
    }

    @Override
    public ResponseEntity<AnalyticsDTO> findAnalyticsById(Long id) {
        try {
            AnalyticsDTO analyticsDTO = businessRepository.findAnalyticsById(id);
            analyticsDTO.setLastSixDays(countReadmesForLastSixDays());
//            businessRepository.findById(id).get().getServices().size();
            return new ResponseEntity<>(analyticsDTO, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return new ResponseEntity<>(new AnalyticsDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<Object> findMostVisited() {
        try {

            List<BusinessDTO> mostVisited = businessRepository.findMostVisited();
            log.info("test " + mostVisited);

            return new ResponseEntity<>(mostVisited, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 108), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<BusinessDTO>> findBusinessByTopRated(BusinessFilter criteria) {
        try {

            Long categoryId = criteria.getCategoryId();
            Long regionId = criteria.getRegionId();
            Float rate = criteria.getRate();
            String searchKey = criteria.getSearchKey();

            int offset = criteria.getOffset();

            return new ResponseEntity<>(businessRepository.findBusinessTopRated(categoryId, regionId, rate
                    , searchKey, PageRequest.of(offset, 100)), HttpStatus.OK);
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> topRate(long longId, Business business) {
        businessRepository.topRate(longId,business.getTop_rate());
        return new ResponseEntity<>("done", HttpStatus.OK);
    }
}
