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

import com.example.back.entity.TipoPago;
import com.example.back.repository.TipoPagoRepository;

@RestController
@RequestMapping("/tipopagos")
public class TipoPagoController {

    @Autowired
    private TipoPagoRepository tipoPagoRepository;

    @GetMapping("/all")
    public ResponseEntity<List<TipoPago>> getAllTipoPagos() {
        List<TipoPago> tipoPagos = tipoPagoRepository.findAll();
        return new ResponseEntity<>(tipoPagos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoPago> createTipoPago(@RequestBody TipoPago tipoPago) {
        TipoPago nuevoTipoPago = tipoPagoRepository.save(tipoPago);
        return new ResponseEntity<>(nuevoTipoPago, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TipoPago> getTipoPagoById(@PathVariable String id) {
        Optional<TipoPago> tipoPago = tipoPagoRepository.findById(id);
        return tipoPago.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoPago> updateTipoPago(@PathVariable String id, @RequestBody TipoPago tipoPagoDetails) {
        return tipoPagoRepository.findById(id)
                .map(tipoPago -> {
                    tipoPago.setTipo(tipoPagoDetails.getTipo());
                    tipoPago.setNro(tipoPagoDetails.getNro());
                    TipoPago updatedTipoPago = tipoPagoRepository.save(tipoPago);
                    return new ResponseEntity<>(updatedTipoPago, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTipoPago(@PathVariable String id) {
        if (tipoPagoRepository.existsById(id)) {
            tipoPagoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
