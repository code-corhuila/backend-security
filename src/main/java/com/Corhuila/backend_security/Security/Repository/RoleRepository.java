package com.Corhuila.backend_security.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Security.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByNameAndStateTrue(String name);
	Optional<Role> findByIdAndStateTrue(Long id);
	
}
