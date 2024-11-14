package com.example.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.back.entity.Test;
import com.example.back.repository.TestRepository;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    // Obtener todos los test
    public List<Test> obtenerTodosLosTests() {
        return testRepository.findAll();
    }

    // Obtener test por ID
    public Optional<Test> obtenerTestPorId(String id) {
        return testRepository.findById(id);
    }

    // Crear un nuevo test
    public Test crearTest(Test test) {
        return testRepository.save(test);
    }

    // Actualizar un test
    public Test actualizarTest(String id, Test testActualizado) {
        return testRepository.findById(id).map(test -> {
            test.setNombre(testActualizado.getNombre());
            test.setFecha(testActualizado.getFecha());
            test.setEstado(testActualizado.getEstado());
            test.setObservaciones(testActualizado.getObservaciones());
            test.setCalificacion(testActualizado.getCalificacion());
            test.setUsuarioId(testActualizado.getUsuarioId());
            test.setCategoriaId(testActualizado.getCategoriaId());
            return testRepository.save(test);
        }).orElseThrow(() -> new RuntimeException("Test no encontrado con el ID: " + id));
    }

    // Eliminar un test
    public void eliminarTest(String id) {
        testRepository.deleteById(id);
    }
}
