package com.example.shopsmart.controller;
import com.example.shopsmart.dto.CreateProductRequest;
import com.example.shopsmart.entity.*;
import com.example.shopsmart.enums.Size;
import com.example.shopsmart.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET /api/products/5
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // GET /api/products/category/Electronics
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    // GET /api/products/search?name=laptop&minPrice=1000&maxPrice=50000
    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") double minPrice,
            @RequestParam(defaultValue = "999999") double maxPrice) {
        return ResponseEntity.ok(productService.searchProducts(name, minPrice, maxPrice));
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody CreateProductRequest req) {
        Product product;
        if ("ELECTRONICS".equalsIgnoreCase(req.getType())) {
            // Electronics product banaya
            ElectronicsProduct ep = new ElectronicsProduct();
            ep.setWarrantyMonths(req.getWarrantyMonths());
            product = ep;
        } else {
            // Clothing product banaya
            ClothingProduct cp = new ClothingProduct();
            cp.setSize(Size.valueOf(req.getSize().toUpperCase()));
            cp.setColor(req.getColor());
            product = cp;
        }

        // common fields set kia
        product.setName(req.getName());
        product.setBasePrice(req.getBasePrice());
        product.setCategory(req.getCategory());
        product.setStockQuantity(req.getStockQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }
    // PUT /api/products/5
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody CreateProductRequest req) {
        Product existing = productService.getProductById(id);
        existing.setName(req.getName());
        existing.setBasePrice(req.getBasePrice());
        existing.setCategory(req.getCategory());
        existing.setStockQuantity(req.getStockQuantity());
        return ResponseEntity.ok(productService.saveProduct(existing));
    }
    // DELETE /api/products/5
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}