package com.control_inventario.services;

import com.control_inventario.Models.Product;
import com.control_inventario.Repositories.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProduct productRepo;

    public List<Product> list() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Long save(Product product) {
        Product savedProduct = productRepo.save(product);
        return savedProduct.getId();
    }

    public void delete(Product product) {
        productRepo.delete(product);
    }
}
