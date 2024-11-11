package com.example.back.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import com.example.back.entity.Usuario;
import com.example.back.service.UsuarioService;


@Component
public class UsuarioResolver{

    @Autowired
    private UsuarioService usuarioService;

    // Resolver para obtener todos los usuarios (Este es un query, no un mutation)
    @QueryMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    // Resolver para obtener un usuario por ID (Este es un query, no un mutation)
    @QueryMapping
    public Usuario getUsuarioById(String user) {
        Optional<Usuario> usuarioOpt = usuarioService.findUsuarioById(user);
        return usuarioOpt.orElse(null); // Retorna null si no se encuentra el usuario
    }

    // Resolver para crear un usuario (Este es un mutation)
    @QueryMapping
    public Usuario createUsuario(
            String user, 
            String password, 
            String nombre, 
            String apellidos, 
            String sexo, 
            String fnac, 
            String telefono, 
            String correo, 
            String rol, 
            String fotoPath, 
            String especialidad, 
            String token) {
        
        // Validación básica de entrada, podrías agregar más según sea necesario
        if (user == null || password == null || nombre == null || correo == null) {
            throw new IllegalArgumentException("Los campos 'user', 'password', 'nombre' y 'correo' son obligatorios");
        }
        
        // Creación del objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setUser(user);
        usuario.setPassword(password); // Asegúrate de encriptar la contraseña
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setSexo(sexo);
        usuario.setFnac(fnac);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setRol(rol);
        usuario.setFotoPath(fotoPath);
        usuario.setEspecialidad(especialidad);
        usuario.setToken(token);
        
        // Llamada al servicio para guardar el usuario
        return usuarioService.createUsuario(usuario);
    }

    // Resolver para actualizar un usuario (Este es un mutation)
    @QueryMapping
     public Usuario updateUsuario(
            String user, 
            String password, 
            String nombre, 
            String apellidos, 
            String sexo, 
            String fnac, 
            String telefono, 
            String correo, 
            String rol, 
            String fotoPath, 
            String especialidad, 
            String token) {
        
        // Buscar el usuario existente
        Optional<Usuario> usuarioOpt = usuarioService.findUsuarioById(user);
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("Usuario con ID " + user + " no encontrado");
        }

        // Obtener los detalles actuales y actualizarlos
        Usuario usuarioDetails = usuarioOpt.get();
        usuarioDetails.setPassword(password);
        usuarioDetails.setNombre(nombre);
        usuarioDetails.setApellidos(apellidos);
        usuarioDetails.setSexo(sexo);
        usuarioDetails.setFnac(fnac);
        usuarioDetails.setTelefono(telefono);
        usuarioDetails.setCorreo(correo);
        usuarioDetails.setRol(rol);
        usuarioDetails.setFotoPath(fotoPath);
        usuarioDetails.setEspecialidad(especialidad);
        usuarioDetails.setToken(token);

        // Llamada al servicio para actualizar el usuario
        return usuarioService.updateUsuario(user, usuarioDetails).orElse(null);
    }

    // Resolver para eliminar un usuario (Este es un mutation)
    @QueryMapping
     public Boolean deleteUsuario(String user) {
        // Verifica si el usuario existe antes de intentar eliminarlo
        Optional<Usuario> usuarioOpt = usuarioService.findUsuarioById(user);
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("Usuario con ID " + user + " no encontrado");
        }

        // Llamada al servicio para eliminar el usuario
        return usuarioService.deleteUsuario(user);
    }
}
