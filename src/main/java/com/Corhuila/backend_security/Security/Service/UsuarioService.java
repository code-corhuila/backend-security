package com.Corhuila.backend_security.Security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Security.Entity.User;
import com.Corhuila.backend_security.Security.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UserRepository usuarioRepository;

    public Optional<User> getByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return usuarioRepository.existsByUsername(username);
    }

    public void save(User usuario){
        usuarioRepository.save(usuario);
    }
}
