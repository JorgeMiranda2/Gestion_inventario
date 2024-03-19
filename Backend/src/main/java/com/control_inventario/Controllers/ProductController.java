package com.control_inventario.Controllers;

import com.control_inventario.Models.Category;
import com.control_inventario.Models.Product;
import com.control_inventario.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        Long savedProduct = productService.save(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        URI location = URI.create("/api/product/" + savedProduct);
        return ResponseEntity.created(location).headers(headers).body("{\"message\": \"Product created successfully\"}");
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = productService.list();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return ResponseEntity.ok().headers(headers).body(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.getProductById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProductOptional = productService.getProductById(id);

        Category category = new Category();
        category.setId(product.getCategory().getId());
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setProductCode(product.getProductCode());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCategory(category);
            existingProduct.setStorageLocation(product.getStorageLocation());

            productService.save(existingProduct);

            return ResponseEntity.ok().body("{\"message\": \"Product updated successfully\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.getProductById(id);

        if (productOptional.isPresent()) {
            productService.delete(productOptional.get());
            return ResponseEntity.ok().body("{\"message\": \"Product deleted successfully\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
