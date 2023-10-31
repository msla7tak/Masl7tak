package com.application.masl7tak.service.serviceImp;

import com.application.masl7tak.Repository.CategoryRepository;
import com.application.masl7tak.constents.Constants;
import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.model.Category;
import com.application.masl7tak.service.CategoryService;
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
public class CategoryServiceImp implements CategoryService {
   private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> findAll(String lang) {
        try {
        if (lang!=null&&lang.equals("en")){
            return new ResponseEntity<List<CategoryDTO>>(categoryRepository.getAllCategory_en(), HttpStatus.OK);

            }
            return new ResponseEntity<List<CategoryDTO>>(categoryRepository.getAllCategory(), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        try {
            log.info("test"+id);

            return new ResponseEntity<Object>(categoryRepository.findCategory(id), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception,1002), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @Override
    public ResponseEntity<Object> save(Category category) {
        try {
            return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception,1003), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> UpdateCategory(Category category) {
        try {
            categoryRepository.UpdateCategory(category.getId(),category.getName());
            return new ResponseEntity<>(Constants.responseMessage("Category Updated",200), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Constants.responseMessage(exception,1003), HttpStatus.INTERNAL_SERVER_ERROR);

        }    }


}
