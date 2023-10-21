package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.*;
import com.application.masl7tak.configs.JwtAuthFilter;
import com.application.masl7tak.constents.Constants;
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
    private  ServicesRepository servicesRepository;
    @Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  JwtAuthFilter jwtAuthFilter;
    @Autowired
    private  BusinessRepository businessRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  AmazonS3Controller amazonS3Controller;





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
            log.info("TEST" + services);
            if (services.getDiscountValue() < 1 && services.getBusiness().getId() != null) {
//                businessRepository.updateStartDiscountVal(services.getBusiness().getId(), services.getDiscountValue());
                Business business = businessRepository.findById(services.getBusiness().getId()).orElseThrow();
                business.setStart_discount_val(business.getStart_discount_val() < services.getDiscountValue() ? services.getDiscountValue() : business.getStart_discount_val());
                businessRepository.save(business);
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
        log.info(business.getId().equals(user.getBusiness_id())+"    "+user.getBusiness_id()+"    "+business.getId());
        if (business.getId().equals(user.getBusiness_id())||jwtAuthFilter.getRole().equals("admin")) {

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
            Float rate = criteria.getRate();
            String searchKey = criteria.getSearchKey();
//            Long regionId = criteria.getRegionId() != null ? criteria.getRegionId() : 1;
            Double minDiscountValue = criteria.getDiscountMinVal();
            Double maxDiscountValue = criteria.getDiscountMaxVal();
            int offset = criteria.getOffset();
            LocalDate currentDate = LocalDate.now();

            return new ResponseEntity<>(servicesRepository.findServicesByCriteria(productId, eventOfferId, businessId, categoryId, regionId, rate,
                    carModel, carBrand, minDiscountValue, maxDiscountValue, searchKey,currentDate, PageRequest.of(offset, 100)), HttpStatus.OK);
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Services> save(Services services, MultipartFile[] files) {
        try {
            if (services.getDiscountValue() < 1 && services.getBusiness().getId() != null) {
//                businessRepository.updateStartDiscountVal(services.getBusiness().getId(), services.getDiscountValue());
                Business business = businessRepository.findById(services.getBusiness().getId()).orElseThrow();
                business.setStart_discount_val(business.getStart_discount_val() < services.getDiscountValue() ? services.getDiscountValue() : business.getStart_discount_val());
                businessRepository.save(business);
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
            if( productService.getBusiness()==null)
                productService.setBusiness(new Business(productService.getBusinessId()));

            Long businessId = productService.getBusiness().getId();
            if (productService.getDiscountValue() < 1 && businessId != null) {
                Business business = businessRepository.findById(businessId).orElseThrow();
                business.setStart_discount_val(business.getStart_discount_val() < productService.getDiscountValue() ? productService.getDiscountValue() : business.getStart_discount_val());
                businessRepository.save(business);
            }

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
            service.setCategory(productService.getCategory()!=null?productService.getCategory():new Category(productService.getCategoryId()));
            service.setQuantity(productService.getQuantity());
            service.setValidUntil(productService.getValidUntil());
            service.setIs_available(productService.getIs_available());
            service.setMax_usage(productService.getMax_usage());
            service.setRate(0);
            service.setSchedule_mode(productService.getSchedule_mode());

            return new ResponseEntity<>(servicesRepository.save(service), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),105), HttpStatus.BAD_REQUEST);

        }
    }

    @Override

    public ResponseEntity<Object> UpdateProductService(ProductService productService, MultipartFile[] files) {

        try {
            productRepository.update(productService.getName(), productService.getDescription(), productService.getProducts_id());

            String image = null;
            if (productService.getImages() != null&&!productService.getImages().equals( ""))
                image=productService.getImages();
//            if (files != null)
//                image = amazonS3Controller.uploadFiles(files);
//            Long ID= productService.getCategory().getId();
//        Category category = categoryRepository.findById(ID).orElseThrow();
            servicesRepository.update(productService.getId(), image, productService.getDiscountValue(), productService.getCarBrand(),
                    productService.getCarModel(), productService.getMax_usage(),
                    productService.getValidUntil(), productService.getIs_available(),productService.schedule_mode);

            return new ResponseEntity<>( servicesRepository.findBy_Id(productService.getId()), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),104), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Object> setService_event(ProductService productService, MultipartFile[] files) {
        try {




            Products products = new Products();
            products.setName(productService.getName());
            products.setDescription(productService.getDescription());


            Services service = new Services();
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
            service.setCategory(productService.getCategory()!=null?productService.getCategory():new Category(productService.getCategoryId()));
            service.setQuantity(productService.getQuantity());
            service.setValidUntil(productService.getValidUntil());
            service.setIs_available(productService.getIs_available());
            service.setMax_usage(productService.getMax_usage());
            service.setRate(0);
            service.setSchedule_mode(productService.getSchedule_mode());

            return new ResponseEntity<>(servicesRepository.save(service), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception.getMessage(),105), HttpStatus.BAD_REQUEST);

        }
    }


}
