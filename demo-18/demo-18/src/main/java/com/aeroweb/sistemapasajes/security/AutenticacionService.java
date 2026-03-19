package com.aeroweb.sistemapasajes.security;

import com.aeroweb.sistemapasajes.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = repository.findByEmail(username);
        if (usuario.isPresent()) {
            return (UserDetails) usuario.get(); 
        }
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}