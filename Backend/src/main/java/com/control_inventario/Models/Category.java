package com.control_inventario.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false, unique = true, length=64)
    private String name;

    @Column(name="status", nullable = false)
    private int status = 1; // Estado por defecto

    @JsonBackReference
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    // Constructor
    public Category(String name) {
        this.name = name;
    }
}