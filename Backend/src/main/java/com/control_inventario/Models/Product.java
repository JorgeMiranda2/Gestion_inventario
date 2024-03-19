package com.control_inventario.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false, unique = true, length=64)
    private String name;

    @Column(name="description", length=255)
    private String description;

    @Column(name="product_code", nullable = false, unique = true, length=16)
    private String productCode;

    @Column(name="storage_location", length=64)
    private String storageLocation;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "product")
    private List<SupplierProduct> supplierProducts;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructor
    public Product(String name, String description, String productCode, String storageLocation) {
        this.name = name;
        this.description = description;
        this.productCode = productCode;
        this.storageLocation = storageLocation;
    }
}
