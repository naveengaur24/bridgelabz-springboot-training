package com.example.shopsmart.repository;

import com.example.shopsmart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Spring khud automatic query banayega — "find by category"
    List<Product> findByCategory(String category);

    // stock > given number wale products
    List<Product> findByStockQuantityGreaterThan(int quantity);

    // price range mein products
    List<Product> findByBasePriceBetween(double min, double max);

    // Custom JPQL query — name se search + price range
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.basePrice BETWEEN :min AND :max")
    List<Product> searchProducts(@Param("name") String name,
                                 @Param("min") double min,
                                 @Param("max") double max);
}