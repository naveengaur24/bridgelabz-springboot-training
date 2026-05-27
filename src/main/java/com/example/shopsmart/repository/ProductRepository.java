package com.example.shopsmart.repository;
import com.example.shopsmart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByStockQuantityGreaterThan(int quantity);
    List<Product> findByBasePriceBetween(double min, double max);
    @Query("""
            select p from Product p where lower(p.name)like lower(concat('%', :name, '%'))""")
    List<Product> searchByName(String name);
}