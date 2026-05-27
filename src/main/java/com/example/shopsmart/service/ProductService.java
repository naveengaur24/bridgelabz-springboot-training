package com.example.shopsmart.service;

import com.example.shopsmart.entity.Product;
import com.example.shopsmart.exception.ProductNotFoundException;
import com.example.shopsmart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    // repository inject karo — constructor injection (best practice)
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // saare products layega
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ek product by ID — nahi mila toh exception
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    // category se filter
    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // name + price range se search
    public List<Product> searchProducts(String name, double min, double max) {
        return productRepository.searchProducts(name, min, max);
    }

    // naya product save kia
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // existing product update kia
    public Product updateProduct(Long id, Product updated) {
        Product existing = getProductById(id); // pehle check karega exist karta hai
        existing.setName(updated.getName());
        existing.setBasePrice(updated.getBasePrice());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setCategory(updated.getCategory());
        return productRepository.save(existing);
    }

    // product delete karega
    public void deleteProduct(Long id) {
        getProductById(id); // pehle check karega exist karta hai
        productRepository.deleteById(id);
    }
}