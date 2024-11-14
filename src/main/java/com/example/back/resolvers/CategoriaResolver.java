package com.example.back.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.back.entity.Categoria;
import com.example.back.service.CategoriaService;

@Controller
public class CategoriaResolver {
    @Autowired
    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaResolver(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Query para obtener todas las categorías
    @QueryMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.obtenerTodasLasCategorias();
    }

    // Query para obtener una categoría por ID
    @QueryMapping
    public Categoria getCategoriaById(@Argument String id) {
        Optional<Categoria> categoria = categoriaService.obtenerCategoriaPorId(id);
        return categoria.orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
    }

    // Mutation para crear una nueva categoría
    @MutationMapping
    public Categoria createCategoria(@Argument String nombre) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        return categoriaService.crearCategoria(categoria);
    }

    // Mutation para actualizar una categoría
    @MutationMapping
    public Categoria updateCategoria(@Argument String id, @Argument String nombre) {
        Categoria categoriaActualizada = new Categoria();
        categoriaActualizada.setNombre(nombre);
        return categoriaService.actualizarCategoria(id, categoriaActualizada);
    }

    // Mutation para eliminar una categoría
    @MutationMapping
    public String deleteCategoria(@Argument String id) {
        categoriaService.eliminarCategoria(id);
        return "Categoría eliminada exitosamente.";
    }
}
