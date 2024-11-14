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

import com.example.back.entity.Resultado;
import com.example.back.repository.ResultadoRepository;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Resultado>> getAllResultados() {
        List<Resultado> resultados = resultadoRepository.findAll();
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Resultado> createResultado(@RequestBody Resultado resultado) {
        Resultado nuevoResultado = resultadoRepository.save(resultado);
        return new ResponseEntity<>(nuevoResultado, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Resultado> getResultadoById(@PathVariable String id) {
        Optional<Resultado> resultado = resultadoRepository.findById(id);
        return resultado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Resultado> updateResultado(@PathVariable String id, @RequestBody Resultado resultadoDetails) {
        return resultadoRepository.findById(id)
                .map(resultado -> {
                    resultado.setResultado(resultadoDetails.getResultado());
                    resultado.setFecha(resultadoDetails.getFecha());
                    resultado.setObservaciones(resultadoDetails.getObservaciones());
                    resultado.setInterpretacion(resultadoDetails.getInterpretacion());
                    resultado.setDetalles(resultadoDetails.getDetalles());
                    resultado.setUrlImagenPath(resultadoDetails.getUrlImagenPath());
                    resultado.setTestId(resultadoDetails.getTestId());
                    Resultado updatedResultado = resultadoRepository.save(resultado);
                    return new ResponseEntity<>(updatedResultado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResultado(@PathVariable String id) {
        if (resultadoRepository.existsById(id)) {
            resultadoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
