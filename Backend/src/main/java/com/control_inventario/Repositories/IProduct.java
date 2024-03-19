package com.control_inventario.Repositories;

import com.control_inventario.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProduct extends JpaRepository<Product, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
