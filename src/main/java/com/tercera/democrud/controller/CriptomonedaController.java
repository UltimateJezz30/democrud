package com.tercera.democrud.controller;

import com.tercera.democrud.model.Criptomoneda;
import com.tercera.democrud.service.CriptomonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/criptomonedas")
public class CriptomonedaController {

    @Autowired
    private CriptomonedaService criptomonedaService;

    @GetMapping
    public List<Criptomoneda> getAllCriptomonedas() {
        return criptomonedaService.getAllCriptomonedas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Criptomoneda> getCriptomonedaById(@PathVariable Long id) {
        Optional<Criptomoneda> criptomoneda = criptomonedaService.getCriptomonedaById(id);
        return criptomoneda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public Criptomoneda createCriptomoneda(@RequestBody Criptomoneda criptomoneda) {
        return criptomonedaService.saveCriptomoneda(criptomoneda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Criptomoneda> updateCriptomoneda(@PathVariable Long id, @RequestBody Criptomoneda criptomonedaDetails) {
        Optional<Criptomoneda> criptomoneda = criptomonedaService.getCriptomonedaById(id);
        if (criptomoneda.isPresent()) {
            Criptomoneda criptomonedaToUpdate = criptomoneda.get();
            criptomonedaToUpdate.setNombre(criptomonedaDetails.getNombre());
            criptomonedaToUpdate.setSimbolo(criptomonedaDetails.getSimbolo());
            criptomonedaToUpdate.setPrecio(criptomonedaDetails.getPrecio());
            return ResponseEntity.ok(criptomonedaService.saveCriptomoneda(criptomonedaToUpdate));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCriptomoneda(@PathVariable Long id) {
        if (criptomonedaService.getCriptomonedaById(id).isPresent()) {
            criptomonedaService.deleteCriptomoneda(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
