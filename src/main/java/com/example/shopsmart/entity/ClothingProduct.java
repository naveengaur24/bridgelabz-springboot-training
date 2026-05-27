package com.example.shopsmart.entity;
import com.example.shopsmart.enums.Size;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("CLOTHING")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClothingProduct extends Product {

    @Enumerated(EnumType.STRING)
    private Size size;

    private String color;

    @Override
    public double calculateFinalPrice() {
        return getBasePrice(); // no extra fee
    }
}