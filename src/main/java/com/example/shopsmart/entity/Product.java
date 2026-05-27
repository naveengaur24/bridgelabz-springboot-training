package com.example.shopsmart.entity;
import com.example.shopsmart.enums.Size;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double basePrice;
    private String category;
    private int stockQuantity;

    @Version
    private Long version;

    public abstract double calculateFinalPrice();

    public boolean isInStock() {
        return stockQuantity > 0;
    }
}