package com.example.back.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.back.entity.Pago;
import com.example.back.repository.PagoRepository;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoRepository pagoRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Pago>> getAllPagos() {
        List<Pago> pagos = pagoRepository.findAll();
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Pago> createPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoRepository.save(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable String id) {
        Optional<Pago> pago = pagoRepository.findById(id);
        return pago.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pago> updatePago(@PathVariable String id, @RequestBody Pago pagoDetails) {
        return pagoRepository.findById(id)
                .map(pago -> {
                    pago.setNombre(pagoDetails.getNombre());
                    pago.setMonto(pagoDetails.getMonto());
                    pago.setTest(pagoDetails.getTest());
                    pago.setTipoPago(pagoDetails.getTipoPago());
                    Pago updatedPago = pagoRepository.save(pago);
                    return new ResponseEntity<>(updatedPago, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable String id) {
        if (pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
