package com.control_inventario.services;

import com.control_inventario.Models.Supplier;
import com.control_inventario.Repositories.ISupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private ISupplier supplierRepo;

    public List<Supplier> list() {
        return supplierRepo.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepo.findById(id);
    }

    public Long save(Supplier supplier) {
        Supplier savedSupplier = supplierRepo.save(supplier);
        return savedSupplier.getId();
    }

    public void delete(Supplier supplier) {
        supplierRepo.delete(supplier);
    }
}
