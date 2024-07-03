package com.Corhuila.backend_security.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Security.Entity.User;
import com.Corhuila.backend_security.Security.Entity.UsuarioPrincipal;
import com.Corhuila.backend_security.Security.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = usuarioRepository.findByUsername(username).get();
        return UsuarioPrincipal.build(usuario);
    }
}
