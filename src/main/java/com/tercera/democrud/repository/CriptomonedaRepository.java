package com.tercera.democrud.repository;

import com.tercera.democrud.model.Criptomoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriptomonedaRepository extends JpaRepository<Criptomoneda, Long> {
    List<Criptomoneda> findAll();
}
