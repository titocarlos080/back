package com.example.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.back.entity.Categoria;
import com.example.back.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Crear una nueva categoría
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Obtener todas las categorías
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    // Obtener una categoría por ID
    public Optional<Categoria> obtenerCategoriaPorId(String id) {
        return categoriaRepository.findById(id);
    }

    // Actualizar una categoría existente
    public Categoria actualizarCategoria(String id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(categoriaActualizada.getNombre());
            return categoriaRepository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
    }

    // Eliminar una categoría por ID
    public void eliminarCategoria(String id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoría no encontrada con el ID: " + id);
        }
    }
}
