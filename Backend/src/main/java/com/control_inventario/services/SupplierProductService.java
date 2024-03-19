package com.control_inventario.services;

import com.control_inventario.Models.SupplierProduct;
import com.control_inventario.Repositories.ISupplierProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierProductService {
    @Autowired
    private ISupplierProduct supplierProductRepo;

    public List<SupplierProduct> list() {
        return supplierProductRepo.findAll();
    }

    public Optional<SupplierProduct> getSupplierProductById(Long id) {
        return supplierProductRepo.findById(id);
    }

    public Long save(SupplierProduct supplierProduct) {
        SupplierProduct savedSupplierProduct = supplierProductRepo.save(supplierProduct);
        return savedSupplierProduct.getId();
    }

    public void delete(SupplierProduct supplierProduct) {
        supplierProductRepo.delete(supplierProduct);
    }
}
