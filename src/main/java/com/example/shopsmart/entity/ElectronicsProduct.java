package com.example.shopsmart.entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@DiscriminatorValue("ELECTRONICS")
public class ElectronicsProduct extends Product {

    private int warrantyMonths;

    @Override
    public double calculateFinalPrice() {

        return getBasePrice()
                + (getBasePrice() * 0.10);
    }

    @PrePersist
    @PreUpdate
    public void validate() {

        if(warrantyMonths <= 0) {
            throw new RuntimeException(
                    "Warranty must be positive");
        }
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }
}
