package com.salesianos.dam.tallerCultural.tallerCultural.repo;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TallerRepository extends JpaRepository<Taller, Long> {

    boolean existsByTituloIgnoreCase(String titulo);
}
