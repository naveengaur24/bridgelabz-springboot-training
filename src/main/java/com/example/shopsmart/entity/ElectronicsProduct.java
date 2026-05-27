package com.example.shopsmart.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("ELECTRONICS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ElectronicsProduct extends Product {

    private int warrantyMonths;

    @Override
    public double calculateFinalPrice() {
        return getBasePrice() * 1.10; // 10% handling fee
    }
}