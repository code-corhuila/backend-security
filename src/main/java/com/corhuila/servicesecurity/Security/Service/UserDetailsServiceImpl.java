package com.corhuila.servicesecurity.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.corhuila.servicesecurity.Security.Entity.User;
import com.corhuila.servicesecurity.Security.Entity.UsuarioPrincipal;
import com.corhuila.servicesecurity.Security.Repository.UserRepository;

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
