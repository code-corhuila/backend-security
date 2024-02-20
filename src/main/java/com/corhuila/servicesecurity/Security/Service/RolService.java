package com.corhuila.servicesecurity.Security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corhuila.servicesecurity.Security.Entity.Role;
import com.corhuila.servicesecurity.Security.Repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    RoleRepository rolRepository;

    public Optional<Role> getByRolNombre(String name){
        return rolRepository.findByNameAndStateTrue(name);
    }
    
    public Optional<Role> getById(Long id) {
    	return rolRepository.findByIdAndStateTrue(id);
    }

    public void save(Role rol){
        rolRepository.save(rol);
    }
}
