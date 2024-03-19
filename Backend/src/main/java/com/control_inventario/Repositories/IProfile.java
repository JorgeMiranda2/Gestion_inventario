package com.control_inventario.Repositories;

import com.control_inventario.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfile extends JpaRepository<Profile,Long> {
}
