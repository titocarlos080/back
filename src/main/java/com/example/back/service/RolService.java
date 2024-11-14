package com.example.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.back.entity.Rol;
 import com.example.back.repository.RolRepository;
 @Service
public class RolService {
     private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // Crear una nueva categoría
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    // Obtener todas las categorías
    public List<Rol> obtenerTodoRoles() {
        return rolRepository.findAll();
    }

    // Obtener una rol por ID
    public Optional<Rol> obtenerRolPorId(String id) {
        return rolRepository.findById(id);
    }

    // Actualizar una categoría existente
    public Rol actualizarRol(String id, Rol rolActualizada) {
        return rolRepository.findById(id).map(rol -> {
            rol.setNombre(rolActualizada.getNombre());
            return rolRepository.save(rol);
        }).orElseThrow(() -> new RuntimeException("Rol no encontrada con el ID: " + id));
    }

    // Eliminar una rol por ID
    public void eliminarRol(String id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rol no encontrada con el ID: " + id);
        }
    }
}
