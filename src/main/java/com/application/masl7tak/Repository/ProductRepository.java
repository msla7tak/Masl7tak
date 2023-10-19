package com.application.masl7tak.Repository;



import com.application.masl7tak.dto.ProductDTO;
import com.application.masl7tak.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    @Query("select P from Products P where P.id=:id")
    Products findBy_Id(@Param("id") Long id);
    @Query(value = "SELECT new com.application.masl7tak.dto.ProductDTO(P.id, P.name, P.description, P.price," +
            " P.image, P.business.id)  " +
            " FROM  Products P  where P.id=:id")
    ProductDTO find_ById(@Param("id") Long id);

    //Long id, String name, String description, double price, int quantity, String image, Long category, Long business
    @Query(value = "SELECT new com.application.masl7tak.dto.ProductDTO(P.id, P.name, P.description, P.price," +
            " P.image, P.business.id) " +
            " FROM  Products P")
    List<ProductDTO> getAllProduct();
    @Modifying
    @Query("UPDATE Products p SET " +
            "p.description = COALESCE(:description, p.description), " +
            "p.name = COALESCE(:name, p.name) " +
            "WHERE p.id = :id")
    void update(String name,String description,Long id);
}
