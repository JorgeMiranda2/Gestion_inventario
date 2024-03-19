package com.control_inventario.Controllers;

import com.control_inventario.Models.SupplierProduct;
import com.control_inventario.services.SupplierProductService;
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
public class SupplierProductController {

    private final SupplierProductService supplierProductService;

    @Autowired
    public SupplierProductController(SupplierProductService supplierProductService) {
        this.supplierProductService = supplierProductService;
    }

    @PostMapping("/supplier-product")
    public ResponseEntity<String> createSupplierProduct(@RequestBody SupplierProduct supplierProduct) {
        Long savedSupplierProduct = supplierProductService.save(supplierProduct);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        URI location = URI.create("/api/supplier-product/" + savedSupplierProduct);
        return ResponseEntity.created(location).headers(headers).body("Supplier product created successfully");
    }

    @GetMapping("/supplier-product")
    public ResponseEntity<List<SupplierProduct>> listSupplierProducts() {
        List<SupplierProduct> supplierProducts = supplierProductService.list();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return ResponseEntity.ok().headers(headers).body(supplierProducts);
    }

    @GetMapping("/supplier-product/{id}")
    public ResponseEntity<SupplierProduct> getSupplierProductById(@PathVariable Long id) {
        Optional<SupplierProduct> supplierProductOptional = supplierProductService.getSupplierProductById(id);

        if (supplierProductOptional.isPresent()) {
            SupplierProduct supplierProduct = supplierProductOptional.get();
            return ResponseEntity.ok().body(supplierProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/supplier-product/{id}")
    public ResponseEntity<String> updateSupplierProduct(@PathVariable Long id, @RequestBody SupplierProduct supplierProduct) {
        Optional<SupplierProduct> existingSupplierProductOptional = supplierProductService.getSupplierProductById(id);

        if (existingSupplierProductOptional.isPresent()) {
            SupplierProduct existingSupplierProduct = existingSupplierProductOptional.get();
            existingSupplierProduct.setCost(supplierProduct.getCost());
            existingSupplierProduct.setQuantity(supplierProduct.getQuantity());
            existingSupplierProduct.setType(supplierProduct.getType());

            supplierProductService.save(existingSupplierProduct);

            return ResponseEntity.ok().body("Supplier product updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/supplier-product/{id}")
    public ResponseEntity<String> deleteSupplierProduct(@PathVariable Long id) {
        Optional<SupplierProduct> supplierProductOptional = supplierProductService.getSupplierProductById(id);

        if (supplierProductOptional.isPresent()) {
            supplierProductService.delete(supplierProductOptional.get());
            return ResponseEntity.ok().body("Supplier product deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
