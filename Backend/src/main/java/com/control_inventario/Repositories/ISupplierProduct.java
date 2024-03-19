package com.control_inventario.Repositories;

import com.control_inventario.Models.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierProduct extends JpaRepository<SupplierProduct, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
