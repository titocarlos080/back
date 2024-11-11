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
    public Optional<Usuario> findUsuarioById(String user) {
        return usuarioRepository.findById(user);
    }

    // Crear un nuevo usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario existente
    public Optional<Usuario> updateUsuario(String user, Usuario usuarioDetails) {
        return usuarioRepository.findById(user)
            .map(usuario -> {
                usuario.setPassword(usuarioDetails.getPassword()); // Recuerda encriptar la contraseña si es necesario
                usuario.setNombre(usuarioDetails.getNombre());
                usuario.setApellidos(usuarioDetails.getApellidos());
                usuario.setSexo(usuarioDetails.getSexo());
                usuario.setFnac(usuarioDetails.getFnac());
                usuario.setTelefono(usuarioDetails.getTelefono());
                usuario.setCorreo(usuarioDetails.getCorreo());
                usuario.setRol(usuarioDetails.getRol());
                usuario.setFotoPath(usuarioDetails.getFotoPath());
                usuario.setEspecialidad(usuarioDetails.getEspecialidad());
                usuario.setToken(usuarioDetails.getToken());
                usuario.setTests(usuarioDetails.getTests());
                return usuarioRepository.save(usuario);
            });
    }

    // Eliminar un usuario
    public boolean deleteUsuario(String user) {
        if (usuarioRepository.existsById(user)) {
            usuarioRepository.deleteById(user);
            return true;
        }
        return false;
    }
}
