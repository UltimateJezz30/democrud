package com.tercera.democrud.service;

import com.tercera.democrud.model.Criptomoneda;
import com.tercera.democrud.repository.CriptomonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriptomonedaService {

    @Autowired
    private CriptomonedaRepository criptomonedaRepository;

    public List<Criptomoneda> getAllCriptomonedas() {
        return criptomonedaRepository.findAll();
    }

    public Optional<Criptomoneda> getCriptomonedaById(Long id) {
        return criptomonedaRepository.findById(id);
    }

    public Criptomoneda saveCriptomoneda(Criptomoneda criptomoneda) {
        return criptomonedaRepository.save(criptomoneda);
    }

    public void deleteCriptomoneda(Long id) {
        criptomonedaRepository.deleteById(id);
    }
}

