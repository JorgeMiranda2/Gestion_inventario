package com.control_inventario.services;

import com.control_inventario.Models.Category;
import com.control_inventario.Repositories.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ICategory categoryRepo;

    public List<Category> list() {
        return categoryRepo.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepo.findById(id);
    }

    public Long save(Category category) {
        Category savedCategory = categoryRepo.save(category);
        return savedCategory.getId();
    }

    public void delete(Category category) {
        categoryRepo.delete(category);
    }
}
