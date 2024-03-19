package com.control_inventario.Repositories;

import com.control_inventario.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategory extends JpaRepository<Category, Long> {

}
