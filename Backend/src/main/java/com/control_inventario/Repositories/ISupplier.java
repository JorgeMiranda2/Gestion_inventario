package com.control_inventario.Repositories;

import com.control_inventario.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplier extends JpaRepository<Supplier, Long> {
}
