package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Usuario;
import com.proyecto.ventaspasajes.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario registrar(Usuario usuario){

        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new RuntimeException("El email ya está registrado");
        }

        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setEstado(true);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario buscar(Integer id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void desactivar(Integer id){

        Usuario usuario = buscar(id);

        usuario.setEstado(false);

        usuarioRepository.save(usuario);
    }
}
