package com.application.masl7tak.Repository;

import com.application.masl7tak.dto.CategoryDTO;
import com.application.masl7tak.dto.EventOffersDTO;
import com.application.masl7tak.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//DAO means Data access object
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT new com.application.masl7tak.dto.CategoryDTO(C.id, C.name, C.image) FROM Category C")
    List<CategoryDTO>getAllCategory();

    @Query("SELECT new com.application.masl7tak.dto.CategoryDTO(C.id, C.name, C.image) FROM Category C where C.id=:id")
    Object findCategory(Long id);
    @Modifying
    @Query("update Category c set c.name =:name  where c.id = :id")
    void UpdateCategory(Long id,String name);
}
