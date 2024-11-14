package com.example.back.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.back.entity.Rol;
import com.example.back.service.RolService;

@Controller
public class RolResolver {

    @Autowired
    private  final RolService rolService;
    
    @Autowired
    public RolResolver(RolService rolService) {
        this.rolService = rolService;
    }

    // Query para obtener todos los roles
    @QueryMapping
    public List<Rol> getAllRoles() {
        return rolService.obtenerTodoRoles();
    }

    // Query para obtener un rol por ID
    @QueryMapping
    public Rol getRolById(@Argument String id) {
        Optional<Rol> rol = rolService.obtenerRolPorId(id);
        return rol.orElseThrow(() -> new RuntimeException("Rol no encontrado con el ID: " + id));
    }

    // Mutation para crear un nuevo rol
    @MutationMapping
    public Rol createRol(@Argument String nombre) {
        Rol rol = new Rol();
        rol.setNombre(nombre);
        return rolService.crearRol(rol);
    }

    // Mutation para actualizar un rol
    @MutationMapping
    public Rol updateRol(@Argument String id, @Argument String nombre) {
        Rol rolActualizado = new Rol();
        rolActualizado.setNombre(nombre);
        return rolService.actualizarRol(id, rolActualizado);
    }

    // Mutation para eliminar un rol
    @MutationMapping
    public String deleteRol(@Argument String id) {
        rolService.eliminarRol(id);
        return "Rol eliminado exitosamente.";
    }
}
