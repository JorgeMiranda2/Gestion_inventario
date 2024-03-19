package com.control_inventario.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "supplier_product")
public class SupplierProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="cost", nullable = false)
    private double cost;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="type", nullable = false, length=16)
    private String type;

    // Constructor
    public SupplierProduct(Supplier supplier, Product product, double costo, int cantidad, String tipoCantidad) {
        this.supplier = supplier;
        this.product = product;
        this.cost = costo;
        this.quantity = cantidad;
        this.type = tipoCantidad;
    }
}
