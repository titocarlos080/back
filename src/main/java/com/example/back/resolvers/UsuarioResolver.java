package com.example.back.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import com.example.back.entity.Usuario;
import com.example.back.service.UsuarioService;

@Controller
public class UsuarioResolver {
    @Autowired
     private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Resolver for getting all users
    @QueryMapping
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        return usuarios;
    }

    // Resolver for getting a user by ID
    @QueryMapping
    public Usuario getUsuarioById(@Argument String id) {
        return usuarioService.findUsuarioById(id).orElse(null);
    }

    // Resolver for creating a new user
    @MutationMapping
    public Usuario createUsuario(
            @Argument String user,
            @Argument String password,
            @Argument String nombre,
            @Argument String apellidos,
            @Argument String sexo,
            @Argument String fnac,
            @Argument String telefono,
            @Argument String correo,
            @Argument String rolId,
            @Argument String fotoPath,
            @Argument String especialidad
            ) {
        // Crear un nuevo objeto Usuario con los datos de los argumentos
        Usuario usuario = new Usuario();
        usuario.setUser(user);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setSexo(sexo);
        usuario.setFnac(fnac);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setRolId(rolId);
        usuario.setFotoPath(fotoPath);
        usuario.setEspecialidad(especialidad);
        usuario.setToken("token");

        // Llamar al servicio para guardar el nuevo usuario
        return usuarioService.createUsuario(usuario);
    }

    // Resolver for updating a user
    @MutationMapping
    public Usuario updateUsuario(
            @Argument String id,
            @Argument String user,
            @Argument String password,
            @Argument String nombre,
            @Argument String apellidos,
            @Argument String sexo,
            @Argument String fnac,
            @Argument String telefono,
            @Argument String correo,
            @Argument String rolId,
            @Argument String fotoPath,
            @Argument String especialidad) {

        Optional<Usuario> usuarioOpt = usuarioService.findUsuarioById(id);

        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }

        // Get the existing user
        Usuario usuario = usuarioOpt.get();

        // Update fields if new values are provided (non-null)
        if (password != null)
            usuario.setPassword(password);
        if (nombre != null)
            usuario.setNombre(nombre);
        if (apellidos != null)
            usuario.setApellidos(apellidos);
        if (sexo != null)
            usuario.setSexo(sexo);
        if (fnac != null)
            usuario.setFnac(fnac);
        if (telefono != null)
            usuario.setTelefono(telefono);
        if (correo != null)
            usuario.setCorreo(correo);
        if (rolId != null)
            usuario.setRolId(rolId);
        if (fotoPath != null)
            usuario.setFotoPath(fotoPath);
        if (especialidad != null)
            usuario.setEspecialidad(especialidad);
        if (usuario.getToken() != null)
            usuario.setToken("token");

        // Save the updated user
        return usuarioService.updateUsuario(usuario);
    }

    // Resolver for deleting a user
    @MutationMapping
    public Boolean deleteUsuario(@Argument String id) {
        Optional<Usuario> usuarioOpt = usuarioService.findUsuarioById(id);
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }

        return usuarioService.deleteUsuario(id);
    }

}
