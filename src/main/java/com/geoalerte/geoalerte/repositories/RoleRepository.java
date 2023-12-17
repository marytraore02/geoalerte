package com.geoalerte.geoalerte.repositories;

import com.geoalerte.geoalerte.models.ERole;
import com.geoalerte.geoalerte.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
