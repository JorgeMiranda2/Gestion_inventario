package com.control_inventario.Controllers;

import com.control_inventario.Models.Category;
import com.control_inventario.services.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        Long savedCategory = categoryService.save(category);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        URI location = URI.create("/api/category/" + savedCategory);
        return ResponseEntity.created(location).headers(headers).body("{\"message\": \"Category created successfully\"}");
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> listCategories() {
        List<Category> categories = categoryService.list();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return ResponseEntity.ok().headers(headers).body(categories);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return ResponseEntity.ok().body(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> existingCategoryOptional = categoryService.getCategoryById(id);

        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            existingCategory.setName(category.getName());
            existingCategory.setStatus(category.getStatus());

            categoryService.save(existingCategory);

            return ResponseEntity.ok().body("{\"message\": \"Category updated successfully\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);

        if (categoryOptional.isPresent()) {
            categoryService.delete(categoryOptional.get());
            return ResponseEntity.ok().body("{\"message\": \"Category deleted successfully\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
