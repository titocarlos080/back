package com.example.back.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.entity.Test;
import com.example.back.repository.TestReposiroty;
 
@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestReposiroty testRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Test> createTest(@RequestBody Test test) {
        Test nuevoTest = testRepository.save(test);
        return new ResponseEntity<>(nuevoTest, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable String id) {
        Optional<Test> test = testRepository.findById(id);
        return test.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable String id, @RequestBody Test testDetails) {
        return testRepository.findById(id)
                .map(test -> {
                    test.setNombre(testDetails.getNombre());
                    test.setFecha(testDetails.getFecha());
                    test.setFechaEntrega(testDetails.getFechaEntrega());
                    test.setEstado(testDetails.getEstado());
                    test.setObservaciones(testDetails.getObservaciones());
                    test.setCalificacion(testDetails.getCalificacion());
                    test.setUsuario(testDetails.getUsuario());
                    test.setCategoria(testDetails.getCategoria());
                    test.setResultado(testDetails.getResultado());
                    test.setPagos(testDetails.getPagos());
                    Test updatedTest = testRepository.save(test);
                    return new ResponseEntity<>(updatedTest, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable String id) {
        if (testRepository.existsById(id)) {
            testRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
