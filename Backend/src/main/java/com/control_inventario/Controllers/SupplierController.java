package com.control_inventario.Controllers;

import com.control_inventario.Models.Supplier;
import com.control_inventario.services.SupplierService;
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
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/supplier")
    public ResponseEntity<String> createSupplier(@RequestBody Supplier supplier) {
        Long savedSupplier = supplierService.save(supplier);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        URI location = URI.create("/api/supplier/" + savedSupplier);
        return ResponseEntity.created(location).headers(headers).body("{\"message\": \"Supplier created successfully\"}");
    }

    @GetMapping("/supplier")
    public ResponseEntity<List<Supplier>> listSuppliers() {
        List<Supplier> suppliers = supplierService.list();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return ResponseEntity.ok().headers(headers).body(suppliers);
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplierOptional = supplierService.getSupplierById(id);

        if (supplierOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            return ResponseEntity.ok().body(supplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<String> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        Optional<Supplier> existingSupplierOptional = supplierService.getSupplierById(id);

        if (existingSupplierOptional.isPresent()) {
            Supplier existingSupplier = existingSupplierOptional.get();
            existingSupplier.setName(supplier.getName());
            existingSupplier.setAccountNumber(supplier.getAccountNumber());
            existingSupplier.setStatus(supplier.getStatus());

            supplierService.save(existingSupplier);

            return ResponseEntity.ok().body("{\"message\": \"Supplier Updated successfully\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        Optional<Supplier> supplierOptional = supplierService.getSupplierById(id);

        if (supplierOptional.isPresent()) {
            supplierService.delete(supplierOptional.get());
            return ResponseEntity.ok().body("{\"message\": \"Supplier Deleted successfully\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
