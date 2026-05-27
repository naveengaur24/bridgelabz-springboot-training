package com.example.shopsmart.catalog;
import com.example.shopsmart.entity.Product;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductCatalog {

    private List<Product> products;

    // Constructor — products list inject hogi
    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    // products set karne ke liye (service se call hoga)
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // category se filter karo
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // sirf wo products jo stock mein hain
    public List<Product> getAvailableProducts() {
        return products.stream()
                .filter(p -> p.getStockQuantity() > 0)
                .collect(Collectors.toList());
    }

    // price se sort karega — ascending ya descending
    public List<Product> getProductsSortedByPrice(boolean ascending) {
        return products.stream()
                .sorted(ascending
                        ? Comparator.comparingDouble(Product::getBasePrice)
                        : Comparator.comparingDouble(Product::getBasePrice).reversed())
                .collect(Collectors.toList());
    }

    // price range ke beech wale products
    public List<Product> getProductsInPriceRange(double min, double max) {
        return products.stream()
                .filter(p -> p.getBasePrice() >= min && p.getBasePrice() <= max)
                .collect(Collectors.toList());
    }

    // total inventory value = price × stock, sab ka sum
    public double calculateTotalInventoryValue() {
        return products.stream()
                .mapToDouble(p -> p.getBasePrice() * p.getStockQuantity())
                .sum();
    }

    // category ke hisaab se group karo — Map<"Electronics", [product1, product2]>
    public Map<String, List<Product>> groupByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
    }

    // saare product names ek string mein — "Laptop, Phone, Shirt"
    public String getProductNamesAsString() {
        return products.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
    }

    // sabse mehanga product — Optional kyunki list empty bhi ho sakti hai
    public Optional<Product> getMostExpensiveProduct() {
        return products.stream().max(Comparator.comparingDouble(Product::getBasePrice));
    }
}