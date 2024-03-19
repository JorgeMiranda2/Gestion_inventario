package com.control_inventario.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false, length=64)
    private String name;

    @Column(name="account_number", nullable = false, unique = true, length=16)
    private String accountNumber;

    @Column(name="status", nullable = false)
    private int status = 1; // Estado por defecto

    @OneToMany(mappedBy = "supplier")
    private List<SupplierProduct> supplierProducts;

    // Constructor
    public Supplier(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }
}
