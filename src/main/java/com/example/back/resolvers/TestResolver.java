package com.example.back.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.back.entity.Test;
import com.example.back.service.TestService;

@Controller
public class TestResolver {
    @Autowired
    private final TestService testService;

    @Autowired
    public TestResolver(TestService testService) {
        this.testService = testService;
    }

    // Query para obtener todos los test
    @QueryMapping
    public List<Test> getAllTests() {
        return testService.obtenerTodosLosTests();
    }

    // Query para obtener un test por ID
    @QueryMapping
    public Test getTestById(@Argument String id) {
        Optional<Test> test = testService.obtenerTestPorId(id);
        return test.orElseThrow(() -> new RuntimeException("Test no encontrado con el ID: " + id));
    }

    // Mutation para crear un nuevo test
    @MutationMapping
    public Test createTest(
            @Argument String nombre,
            @Argument String fecha,
            @Argument String estado,
            @Argument String observaciones,
            @Argument Integer calificacion,
            @Argument String usuarioId,
            @Argument String categoriaId) {
        Test test = new Test();
        test.setNombre(nombre);
        test.setFecha(fecha);
        test.setEstado(estado);
        test.setObservaciones(observaciones);
        test.setCalificacion(calificacion);
        test.setCategoriaId(categoriaId);
        test.setUsuarioId(usuarioId);

        return testService.crearTest(test);
    }

    // Mutation para actualizar un test
    @MutationMapping
    public Test updateTest(
            @Argument String id,
            @Argument String nombre,
            @Argument String fecha,
            @Argument String estado,
            @Argument String observaciones,
            @Argument Integer calificacion,
            @Argument String usuarioId,
            @Argument String categoriaId) {
        Test testActualizado = new Test();
        testActualizado.setNombre(nombre);
        testActualizado.setFecha(fecha);
        testActualizado.setEstado(estado);
        testActualizado.setObservaciones(observaciones);
        testActualizado.setCalificacion(calificacion);
        testActualizado.setCategoriaId(categoriaId);
        testActualizado.setUsuarioId(usuarioId);
        return testService.actualizarTest(id, testActualizado);
    }

    // Mutation para eliminar un test
    @MutationMapping
    public String deleteTest(@Argument String id) {
        testService.eliminarTest(id);
        return "Test eliminado exitosamente.";
    }
}
