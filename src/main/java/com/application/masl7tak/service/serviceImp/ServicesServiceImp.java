package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.*;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.CarModelDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.*;
import com.application.masl7tak.model.filter.ServicesFilter;
import com.application.masl7tak.rest.controller.AmazonS3Controller;
import com.application.masl7tak.service.ServicesService;
import com.application.masl7tak.dto.ServicesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
public class ServicesServiceImp implements ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AmazonS3Controller amazonS3Controller;


    @Override
    public ResponseEntity<List<ServicesDTO>> findAll_with_offset(int offset) {
        try {

            List<ServicesDTO> servicesDTO = servicesRepository.getAll_Services(PageRequest.of(offset, 10));

//            for (ServicesDTO service : servicesDTO) {
//                log.info("test:" + service);
//                service.setProducts(productRepository.find_ById(service.getProducts_id()));
//
//            }

            return new ResponseEntity<>(servicesDTO, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ServicesDTO>> findAll() {
        try {

            List<ServicesDTO> servicesDTO = servicesRepository.getAll_Services();

            return new ResponseEntity<>(servicesDTO, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ServicesDTO> findById(Long id) {
        try {
            servicesRepository.visits_num(id);

            return new ResponseEntity<ServicesDTO>(servicesRepository.findBy_Id(id), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ServicesDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Services> save(Services services) {
        try {
            businessRepository.serviceCount(services.getBusiness().getId());
            log.info("TEST" + services);
            if (services.getDiscountValue() < 1 && services.getBusiness().getId() != null) {
//                businessRepository.updateStartDiscountVal(services.getBusiness().getId(), services.getDiscountValue());
                Business business = businessRepository.findById(services.getBusiness().getId()).orElseThrow();
                if (business.getStart_discount_val() < services.getDiscountValue()) {
                    businessRepository.startDiscountVal(business.getId(), services.getDiscountValue());
                }
                businessRepository.serviceCount(business.getId());
            }

            return new ResponseEntity<>(servicesRepository.save(services), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Services(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteById(Long id) {
        Services services = servicesRepository.findById(id).orElseThrow();

        Business business = services.getBusiness();

        User user = userRepository.findUserByEmail(jwtAuthFilter.getCurrentUser()).orElseThrow();
        log.info(business.getId().equals(user.getBusiness_id()) + "    " + user.getBusiness_id() + "    " + business.getId());
        if (business.getId().equals(user.getBusiness_id()) || jwtAuthFilter.getRole().equals("admin")) {
            businessRepository.serviceDeCount(business.getId());

            productRepository.delete(services.getProducts());
            servicesRepository.deleteById(id);
            return;

        }
        log.info("error");


    }


    @Override
    public ResponseEntity<List<ServicesDTO>> findServicesByCriteria(ServicesFilter criteria) {
        try {

//            if (criteria.get("productId").equals("111")) {
//            if (criteria.get("productId").equals("111")) {
//                return new ResponseEntity<>(servicesRepository.getAll_Services(), HttpStatus.OK);
//            } else {
            Long productId = criteria.getProductId();
            Long eventOfferId = criteria.getEventId();
            Long businessId = criteria.getBusinessId();
            Long categoryId = criteria.getCategoryId();
            Long carModel = criteria.getModelId();
            Long carBrand = criteria.getBrandId();
            Long regionId = criteria.getRegionId();
            Long cityId = criteria.getCityId();
            Float rate = criteria.getRate();
            String searchKey = criteria.getSearchKey();
//            Long regionId = criteria.getRegionId() != null ? criteria.getRegionId() : 1;
            Double minDiscountValue = criteria.getDiscountMinVal();
            Double maxDiscountValue = criteria.getDiscountMaxVal();
            int offset = criteria.getOffset();
            LocalDate currentDate = LocalDate.now();

            List<ServicesDTO> servicesDTOS = servicesRepository.findServicesByCriteria(productId, eventOfferId, businessId, categoryId, regionId,cityId, rate,
                    carModel, carBrand, minDiscountValue, maxDiscountValue, searchKey, currentDate, PageRequest.of(offset, 100));
            for (ServicesDTO services : servicesDTOS) {
                services.setCarBrandEntities(servicesRepository.findBrand(services.getId()));
                services.setCarModelEntities(servicesRepository.findModel(services.getId()));

            }
            return new ResponseEntity<>(servicesDTOS, HttpStatus.OK);
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Services> save(Services services, MultipartFile[] files) {
        try {
            businessRepository.serviceCount(services.getBusiness().getId());

            if (services.getDiscountValue() < 1 && services.getBusiness().getId() != null) {
//                businessRepository.updateStartDiscountVal(services.getBusiness().getId(), services.getDiscountValue());
                Business business = businessRepository.findById(services.getBusiness().getId()).orElseThrow();
                if (business.getStart_discount_val() < services.getDiscountValue()) {
                    businessRepository.startDiscountVal(business.getId(), services.getDiscountValue());
                }
            }
            services.setImages(amazonS3Controller.uploadFiles(files));

            return new ResponseEntity<>(servicesRepository.save(services), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new Services(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> setProductService(ProductService productService, MultipartFile[] files) {
        try {

            if (productService.getBusiness() == null)
                productService.setBusiness(new Business(productService.getBusinessId()));

            Long businessId = productService.getBusiness().getId();
            if (productService.getDiscountValue() < 1 && businessId != null) {
                Business business = businessRepository.findById(businessId).orElseThrow();
                if (business.getStart_discount_val() < productService.getDiscountValue()) {
                    businessRepository.startDiscountVal(business.getId(), productService.getDiscountValue());
                }
            }
            businessRepository.serviceCount(businessId);


            Products products = new Products();
            products.setName(productService.getName());
            products.setDescription(productService.getDescription());
            products.setBusiness(productService.getBusiness());


            Services service = new Services();
            LocalDate today = LocalDate.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(dateFormat);
            service.setCreationDate(formattedDate);
            service.setDiscountValue(productService.getDiscountValue());
            service.setProducts(productRepository.save(products));
            service.setBusiness(productService.getBusiness());
            if (productService.getImages() != null) service.setImages(productService.getImages());
            if (files != null) service.setImages(amazonS3Controller.uploadFiles(files));
            service.setCarBrand(productService.getCarBrand());
            service.setCarModel(productService.getCarModel());
            Long ID = productService.getCategoryId();
            Category category = categoryRepository.findById(ID).orElse(null);

            service.setCategory(category);
            service.setQuantity(productService.getQuantity());
            service.setValidUntil(productService.getValidUntil());
            service.setIs_available(productService.getIs_available());
            service.setMax_usage(productService.getMax_usage());
            service.setRate(5);
            service.setSchedule_mode(productService.getSchedule_mode());
            if (productService.getCarModelEntities() != null) {
                for (CarModelEntity carModelEntity : productService.getCarModelEntities()) {
                    carModelEntity.setServices(service);
                    service.setCarModel(carModelEntity.getModelId());
                }
                service.setCarModelEntities(productService.getCarModelEntities());
            }
            else {
                CarModelEntity carModel = new CarModelEntity();
                carModel.setServices(service);
                carModel.setModelId(0L);
                List<CarModelEntity> carModelEntities = new ArrayList<>();
                carModelEntities.add(carModel);
                service.setCarBrand(0L);
                service.setCarModelEntities(carModelEntities);

            }
            service.setSchedule_mode(productService.getSchedule_mode());
            if (productService.getCarBrandEntities() != null) {
                for (CarBrandEntity carBrandEntity : productService.getCarBrandEntities()) {
                    carBrandEntity.setServices(service);
                    service.setCarBrand(carBrandEntity.getBrandId());

                }

                service.setCarBrandEntities(productService.getCarBrandEntities());
            } else {
                CarBrandEntity carBrand = new CarBrandEntity();
                carBrand.setServices(service);
                carBrand.setBrandId(0L);
                List<CarBrandEntity> carBrandEntity = new ArrayList<>();
                carBrandEntity.add(carBrand);
                service.setCarBrand(0L);
                service.setCarBrandEntities(carBrandEntity);

            }
            return new ResponseEntity<>(servicesRepository.findBy_Id(servicesRepository.save(service).getId()), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 105), HttpStatus.BAD_REQUEST);

        }
    }

    @Override

    public ResponseEntity<Object> UpdateProductService(ProductService productService, MultipartFile[] files) {

        try {
            log.info("test" + productService);
            productRepository.update(productService.getName(), productService.getDescription(), productService.getProducts_id());

            String image = null;
            if (productService.getImages() != null && !productService.getImages().equals(""))
                image = productService.getImages();
//            if (files != null)
//                image = amazonS3Controller.uploadFiles(files);
            Long ID = productService.getCategoryId();
            Category category = new Category();
            category.setId(ID);
            log.info("Category ID: " + ID);
            log.info("Category Name: " + category.getName());


            servicesRepository.update(productService.getId(), image, productService.getDiscountValue(), productService.getCarBrand(),
                    productService.getCarModel(), productService.getMax_usage(),
                    productService.getValidUntil(), productService.getIs_available(), productService.schedule_mode, ID);

            Services service = servicesRepository.findById(productService.getId()).get();
            servicesRepository.clearCarBrandEntities(service.getId());
            servicesRepository.clearCarModelEntities(service.getId());
            if (productService.getCarModelEntities() != null) {

                for (CarModelEntity carModelEntity : productService.getCarModelEntities()) {
                    carModelEntity.setServices(service);
                }
                service.setCarModelEntities(productService.getCarModelEntities());
            }
            else {
                CarModelEntity carModel = new CarModelEntity();
                carModel.setServices(service);
                carModel.setModelId(0L);
                List<CarModelEntity> carModelEntities = new ArrayList<>();
                carModelEntities.add(carModel);
                service.setCarBrand(0L);
                service.setCarModelEntities(carModelEntities);

            }

            if (productService.getCarBrandEntities() != null) {

                for (CarBrandEntity carBrandEntity : productService.getCarBrandEntities()) {
                    carBrandEntity.setServices(service);

                }
                service.setCarBrandEntities(productService.getCarBrandEntities());
            }
            servicesRepository.save(service);
            return new ResponseEntity<>(servicesRepository.findBy_Id(productService.getId()), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 104), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Object> setService_event(ProductService productService, MultipartFile[] files) {
        try {
            if (productService.getBusiness() == null)
                productService.setBusiness(new Business(productService.getBusinessId()));


            Products products = new Products();
            products.setName(productService.getName());
            products.setDescription(productService.getDescription());
            products.setBusiness(productService.getBusiness());
            businessRepository.serviceCount(productService.getBusiness().getId());

            Services service = new Services();
            service.setBusiness(productService.getBusiness());
            service.setEventOffers(new EventOffers(productService.getEventId()));
            LocalDate today = LocalDate.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(dateFormat);
            service.setCreationDate(formattedDate);
            service.setDiscountValue(productService.getDiscountValue());
            service.setProducts(productRepository.save(products));
            if (productService.getImages() != null) service.setImages(productService.getImages());
            if (files != null) service.setImages(amazonS3Controller.uploadFiles(files));
            service.setCarBrand(productService.getCarBrand());
            service.setCarModel(productService.getCarModel());
            Long ID = productService.getCategoryId();
            Category category = categoryRepository.findById(ID).orElse(null);

            service.setCategory(category);
            service.setQuantity(productService.getQuantity());
            service.setValidUntil(productService.getValidUntil());
            service.setIs_available(productService.getIs_available());
            service.setMax_usage(productService.getMax_usage());
            service.setRate(5);
            service.setSchedule_mode(productService.getSchedule_mode());

            return new ResponseEntity<>(servicesRepository.save(service), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 105), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<String> active(long longId) {
        servicesRepository.active(longId, "true");
        return new ResponseEntity<>("done", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ServicesDTO>> findAllAdmin(ServicesFilter criteria) {
        try {

//            if (criteria.get("productId").equals("111")) {
//            if (criteria.get("productId").equals("111")) {
//                return new ResponseEntity<>(servicesRepository.getAll_Services(), HttpStatus.OK);
//            } else {
            Long productId = criteria.getProductId();
            Long eventOfferId = criteria.getEventId();
            Long businessId = criteria.getBusinessId();
            Long categoryId = criteria.getCategoryId();
            Long carModel = criteria.getModelId();
            Long carBrand = criteria.getBrandId();
            Long regionId = criteria.getRegionId();
            Float rate = criteria.getRate();
            String searchKey = criteria.getSearchKey();
//            Long regionId = criteria.getRegionId() != null ? criteria.getRegionId() : 1;
            Double minDiscountValue = criteria.getDiscountMinVal();
            Double maxDiscountValue = criteria.getDiscountMaxVal();
            int offset = criteria.getOffset();

            return new ResponseEntity<>(servicesRepository.findAllAdmin(productId, eventOfferId, businessId, categoryId, regionId, rate,
                    carModel, carBrand, minDiscountValue, maxDiscountValue, searchKey, PageRequest.of(offset, 100)), HttpStatus.OK);
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> findMaxAmount() {
        try {

            return new ResponseEntity<>(servicesRepository.findMaxValueField(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 105), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Object> findMostVisited() {
        try {

            return new ResponseEntity<>(servicesRepository.findMostVisited(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(), 105), HttpStatus.BAD_REQUEST);

        }
    }


}
