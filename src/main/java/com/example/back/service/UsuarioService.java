package com.example.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.entity.Usuario;
import com.example.back.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por ID (user en este caso)
    public Optional<Usuario> findUsuarioById(String id) {
        return usuarioRepository.findById(id);
    }

    // Crear un nuevo usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario existente
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario); // Save updates to the existing user
    }

    // Eliminar un usuario
    public boolean deleteUsuario(String id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
